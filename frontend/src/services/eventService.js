import api from './api'

export const eventService = {
  list: (params) => api.get('/events', { params }),
  details: (id) => api.get(`/events/${id}`),
  calendar: (params) => api.get('/events/calendar', { params }),
  mine: () => api.get('/events/mine'),
  assignedVolunteer: () => api.get('/events/assigned/volunteer'),
  create: (payload) => api.post('/events', payload),
  update: (id, payload) => api.put(`/events/${id}`, payload),
  publish: (id) => api.post(`/events/${id}/publish`),
  register: (eventId) => api.post(`/registrations/events/${eventId}`),
  myRegistrations: () => api.get('/registrations/my'),
  participants: (eventId) => api.get(`/registrations/events/${eventId}`),
}
