import { Navigate, Route, Routes } from 'react-router-dom'
import ProtectedRoute from '../components/ProtectedRoute'
import AppLayout from '../layouts/AppLayout'
import AuthLayout from '../layouts/AuthLayout'
import AdminPage from '../pages/AdminPage'
import AnnouncementsPage from '../pages/AnnouncementsPage'
import CalendarPage from '../pages/CalendarPage'
import CertificatesPage from '../pages/CertificatesPage'
import DashboardPage from '../pages/DashboardPage'
import EventDetailsPage from '../pages/EventDetailsPage'
import EventsPage from '../pages/EventsPage'
import LoginPage from '../pages/LoginPage'
import MyRegistrationsPage from '../pages/MyRegistrationsPage'
import NotFoundPage from '../pages/NotFoundPage'
import OrganizerPage from '../pages/OrganizerPage'
import ProfilePage from '../pages/ProfilePage'
import RegisterPage from '../pages/RegisterPage'
import ResetPasswordPage from '../pages/ResetPasswordPage'
import VerifyCertificatePage from '../pages/VerifyCertificatePage'
import VerifyEmailPage from '../pages/VerifyEmailPage'
import VolunteerPage from '../pages/VolunteerPage'

export default function AppRouter() {
  return (
    <Routes>
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/reset-password" element={<ResetPasswordPage />} />
      </Route>

      <Route path="/verify-email" element={<VerifyEmailPage />} />
      <Route path="/verify-certificate" element={<VerifyCertificatePage />} />

      <Route element={<ProtectedRoute />}>
        <Route element={<AppLayout />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/events" element={<EventsPage />} />
          <Route path="/events/:id" element={<EventDetailsPage />} />
          <Route path="/calendar" element={<CalendarPage />} />
          <Route path="/announcements" element={<AnnouncementsPage />} />
          <Route path="/profile" element={<ProfilePage />} />
        </Route>
      </Route>

      <Route element={<ProtectedRoute roles={['ROLE_STUDENT', 'ROLE_FACULTY']} />}>
        <Route element={<AppLayout />}>
          <Route path="/registrations" element={<MyRegistrationsPage />} />
        </Route>
      </Route>

      <Route element={<ProtectedRoute roles={['ROLE_STUDENT', 'ROLE_FACULTY', 'ROLE_ADMIN', 'ROLE_ORGANIZER']} />}>
        <Route element={<AppLayout />}>
          <Route path="/certificates" element={<CertificatesPage />} />
        </Route>
      </Route>

      <Route element={<ProtectedRoute roles={['ROLE_ADMIN']} />}>
        <Route element={<AppLayout />}>
          <Route path="/admin" element={<AdminPage />} />
        </Route>
      </Route>

      <Route element={<ProtectedRoute roles={['ROLE_ORGANIZER', 'ROLE_ADMIN']} />}>
        <Route element={<AppLayout />}>
          <Route path="/organizer" element={<OrganizerPage />} />
        </Route>
      </Route>

      <Route element={<ProtectedRoute roles={['ROLE_VOLUNTEER']} />}>
        <Route element={<AppLayout />}>
          <Route path="/volunteer" element={<VolunteerPage />} />
        </Route>
      </Route>

      <Route path="/" element={<Navigate to="/dashboard" replace />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  )
}
