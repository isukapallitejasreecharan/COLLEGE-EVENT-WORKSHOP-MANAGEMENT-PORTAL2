import api from './api'

export const authService = {
  login: (payload) => api.post('/auth/login', payload),
  register: (payload) => api.post('/auth/register', payload),
  me: () => api.get('/auth/me'),
  forgotPassword: (payload) => api.post('/auth/forgot-password', payload),
  resetPassword: (payload) => api.post('/auth/reset-password', payload),
  verifyEmail: (token) => api.get(`/auth/verify-email?token=${token}`),
  logout: (refreshToken) => api.post(`/auth/logout?refreshToken=${refreshToken}`),
}
