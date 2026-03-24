import { createContext, useCallback, useEffect, useMemo, useState } from 'react'
import toast from 'react-hot-toast'
import { authService } from '../services/authService'

export const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null)
  const [roles, setRoles] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const handleAuthCleared = () => {
      setUser(null)
      setRoles([])
      setLoading(false)
    }

    window.addEventListener('portal:auth-cleared', handleAuthCleared)

    const token = localStorage.getItem('accessToken')
    if (!token) {
      setLoading(false)
      return () => window.removeEventListener('portal:auth-cleared', handleAuthCleared)
    }

    authService.me()
      .then(({ data }) => {
        setUser(data.data)
        setRoles(data.data.roles || [])
      })
      .catch(() => {
        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
        setUser(null)
        setRoles([])
      })
      .finally(() => setLoading(false))

    return () => window.removeEventListener('portal:auth-cleared', handleAuthCleared)
  }, [])

  const applySessionUser = useCallback((nextUser) => {
    setUser(nextUser)
    setRoles(nextUser?.roles || [])
  }, [])

  const login = async (payload) => {
    const { data } = await authService.login(payload)
    localStorage.setItem('accessToken', data.data.accessToken)
    localStorage.setItem('refreshToken', data.data.refreshToken)
    applySessionUser(data.data.user)
    toast.success('Welcome back')
    return data.data
  }

  const register = async (payload) => {
    const { data } = await authService.register(payload)
    localStorage.setItem('accessToken', data.data.accessToken)
    localStorage.setItem('refreshToken', data.data.refreshToken)
    applySessionUser(data.data.user)
    toast.success('Account created')
    return data.data
  }

  const logout = async () => {
    const refreshToken = localStorage.getItem('refreshToken')
    if (refreshToken) {
      try {
        await authService.logout(refreshToken)
      } catch {
      }
    }
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
    setUser(null)
    setRoles([])
  }

  const value = useMemo(() => ({
    user,
    roles,
    loading,
    isAuthenticated: !!user,
    login,
    register,
    logout,
    setSessionUser: applySessionUser,
  }), [user, roles, loading, applySessionUser])

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}
