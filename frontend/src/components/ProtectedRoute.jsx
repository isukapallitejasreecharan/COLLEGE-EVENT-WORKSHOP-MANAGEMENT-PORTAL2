import { Navigate, Outlet } from 'react-router-dom'
import LoadingSpinner from './LoadingSpinner'
import { useAuth } from '../hooks/useAuth'

export default function ProtectedRoute({ roles }) {
  const { loading, isAuthenticated, roles: currentRoles } = useAuth()

  if (loading) {
    return <LoadingSpinner label="Checking access..." />
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />
  }

  if (roles?.length && !roles.some((role) => currentRoles.includes(role))) {
    return <Navigate to="/dashboard" replace />
  }

  return <Outlet />
}
