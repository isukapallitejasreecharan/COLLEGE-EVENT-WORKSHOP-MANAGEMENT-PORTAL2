import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import { eventService } from '../services/eventService'

const initialForm = {
  title: '',
  description: '',
  categoryId: 1,
  eventType: 'Workshop',
  startDate: '',
  endDate: '',
  startTime: '10:00',
  endTime: '16:00',
  registrationDeadline: '',
  maximumParticipants: 50,
  visibility: 'PUBLIC',
  multiDay: false,
  recurring: false,
  registrationRequiresApproval: false,
  registrationClosed: false,
  publishNow: true,
  sessions: [],
  speakerIds: [],
}

export default function OrganizerPage() {
  const [events, setEvents] = useState([])
  const [form, setForm] = useState(initialForm)

  const loadEvents = () => eventService.mine()
    .then(({ data }) => setEvents(data.data || []))
    .catch((error) => {
      toast.error(error.response?.data?.message || 'Unable to load organizer events')
      setEvents([])
    })

  useEffect(() => { loadEvents() }, [])

  const createEvent = async (event) => {
    event.preventDefault()
    try {
      await eventService.create(form)
      toast.success('Event created')
      setForm(initialForm)
      loadEvents()
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to create event')
    }
  }

  return (
    <div className="grid gap-6 xl:grid-cols-[0.9fr_1.1fr]">
      <form onSubmit={createEvent} className="glass-panel rounded-[28px] p-6">
        <p className="text-sm uppercase tracking-[0.4em] text-orange-500">Organizer hub</p>
        <h2 className="mt-3 text-3xl font-bold">Create an event</h2>
        <div className="mt-6 space-y-3">
          <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Title" value={form.title} onChange={(e) => setForm({ ...form, title: e.target.value })} />
          <textarea className="h-32 w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Description" value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} />
          <div className="grid gap-3 sm:grid-cols-2">
            <input type="date" className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.startDate} onChange={(e) => setForm({ ...form, startDate: e.target.value, endDate: e.target.value })} />
            <input type="datetime-local" className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.registrationDeadline} onChange={(e) => setForm({ ...form, registrationDeadline: e.target.value })} />
          </div>
          <div className="grid gap-3 sm:grid-cols-2">
            <input type="time" className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.startTime} onChange={(e) => setForm({ ...form, startTime: e.target.value })} />
            <input type="time" className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.endTime} onChange={(e) => setForm({ ...form, endTime: e.target.value })} />
          </div>
          <input type="number" className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.maximumParticipants} onChange={(e) => setForm({ ...form, maximumParticipants: Number(e.target.value) })} />
          <button className="w-full rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Create event</button>
        </div>
      </form>
      <div className="space-y-4">
        {events.map((item) => (
          <div key={item.id} className="glass-panel rounded-[28px] p-6">
            <div className="flex items-center justify-between gap-4">
              <div>
                <h3 className="text-xl font-bold">{item.title}</h3>
                <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">{item.eventType} ? {item.status} ? {item.registeredParticipants}/{item.maximumParticipants}</p>
              </div>
              <button onClick={() => eventService.publish(item.id).then(() => loadEvents()).catch((error) => toast.error(error.response?.data?.message || 'Unable to publish event'))} className="rounded-full border border-emerald-300 px-4 py-2 text-sm font-semibold text-emerald-700 dark:border-emerald-500/40 dark:text-emerald-300">Publish</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
