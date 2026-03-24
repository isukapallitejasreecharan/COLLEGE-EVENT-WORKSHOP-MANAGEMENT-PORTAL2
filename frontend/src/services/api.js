import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
})

const clearStoredAuth = () => {
  localStorage.removeItem('accessToken')
  localStorage.removeItem('refreshToken')
  window.dispatchEvent(new Event('portal:auth-cleared'))
}

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('accessToken')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

let refreshing = null

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config || {}
    const status = error.response?.status
    const isRefreshCall = originalRequest.url?.includes('/auth/refresh')

    if (status === 401 && !originalRequest._retry && !isRefreshCall) {
      originalRequest._retry = true
      const refreshToken = localStorage.getItem('refreshToken')
      if (!refreshToken) {
        clearStoredAuth()
        return Promise.reject(error)
      }

      refreshing ||= axios.post('/api/auth/refresh', { refreshToken })

      try {
        const { data } = await refreshing
        localStorage.setItem('accessToken', data.data.accessToken)
        localStorage.setItem('refreshToken', data.data.refreshToken)
        refreshing = null
        originalRequest.headers = originalRequest.headers || {}
        originalRequest.headers.Authorization = `Bearer ${data.data.accessToken}`
        return api(originalRequest)
      } catch (refreshError) {
        refreshing = null
        clearStoredAuth()
        return Promise.reject(refreshError)
      }
    }

    if ((status === 401 || status === 400) && isRefreshCall) {
      refreshing = null
      clearStoredAuth()
    }

    return Promise.reject(error)
  },
)

export default api
