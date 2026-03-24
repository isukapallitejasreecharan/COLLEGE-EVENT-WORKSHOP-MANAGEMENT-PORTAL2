import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import { eventService } from '../services/eventService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function VolunteerPage() {
  const [events, setEvents] = useState(null)

  useEffect(() => {
    eventService.assignedVolunteer()
      .then(({ data }) => setEvents(data.data || []))
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load volunteer assignments')
        setEvents([])
      })
  }, [])

  if (!events) return <LoadingSpinner label="Loading volunteer assignments..." />

  return (
    <div className="space-y-4">
      {events.map((event) => (
        <div key={event.id} className="glass-panel rounded-[28px] p-6">
          <h2 className="text-2xl font-bold">{event.title}</h2>
          <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">{event.startDate} ? {event.venueName}</p>
          <p className="mt-4 text-sm">Use the attendance tools from the organizer workflow to help check participants in and support onsite operations.</p>
        </div>
      ))}
      {!events.length && <div className="glass-panel rounded-[28px] p-6">No volunteer assignments yet.</div>}
    </div>
  )
}
