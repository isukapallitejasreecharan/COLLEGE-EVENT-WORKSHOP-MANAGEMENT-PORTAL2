import api from './api'

export const dashboardService = {
  admin: () => api.get('/admin/dashboard'),
  notifications: () => api.get('/notifications/my'),
  unreadCount: () => api.get('/notifications/unread-count'),
  announcements: () => api.get('/announcements'),
  myCertificates: () => api.get('/certificates/my'),
}
