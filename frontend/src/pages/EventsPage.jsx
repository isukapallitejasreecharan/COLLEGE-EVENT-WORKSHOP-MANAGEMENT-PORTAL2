import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import EventCard from '../components/EventCard'
import LoadingSpinner from '../components/LoadingSpinner'
import { useAuth } from '../hooks/useAuth'
import { useDebounce } from '../hooks/useDebounce'
import { eventService } from '../services/eventService'

export default function EventsPage() {
  const { roles } = useAuth()
  const [search, setSearch] = useState('')
  const [events, setEvents] = useState([])
  const [loading, setLoading] = useState(true)
  const debouncedSearch = useDebounce(search)
  const canRegister = roles.includes('ROLE_STUDENT') || roles.includes('ROLE_FACULTY')

  useEffect(() => {
    setLoading(true)
    eventService.list({ page: 0, size: 12, search: debouncedSearch, direction: 'ASC' })
      .then(({ data }) => setEvents(data.data.content || []))
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load events')
        setEvents([])
      })
      .finally(() => setLoading(false))
  }, [debouncedSearch])

  const handleRegister = async (id) => {
    try {
      await eventService.register(id)
      toast.success('Registration submitted')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to register')
    }
  }

  if (loading) return <LoadingSpinner label="Loading events..." />

  return (
    <div className="space-y-6">
      <div className="glass-panel rounded-[28px] p-6">
        <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <p className="text-sm uppercase tracking-[0.4em] text-orange-500">Explore</p>
            <h2 className="mt-2 font-display text-3xl font-bold">Discover workshops, seminars, and hackathons</h2>
          </div>
          <input value={search} onChange={(e) => setSearch(e.target.value)} placeholder="Search by title, description, or tags" className="w-full rounded-2xl border border-slate-200 px-4 py-3 lg:max-w-md dark:border-slate-700 dark:bg-slate-800" />
        </div>
      </div>
      <div className="grid gap-5 xl:grid-cols-3">
        {events.map((event) => (
          <EventCard
            key={event.id}
            event={event}
            action={canRegister ? <button onClick={() => handleRegister(event.id)} className="rounded-full border border-emerald-300 px-4 py-2 text-sm font-semibold text-emerald-700 dark:border-emerald-500/40 dark:text-emerald-300">Register</button> : null}
          />
        ))}
      </div>
    </div>
  )
}
