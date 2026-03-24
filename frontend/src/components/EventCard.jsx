import { Link } from 'react-router-dom'
import dayjs from 'dayjs'

export default function EventCard({ event, action }) {
  return (
    <div className="glass-panel overflow-hidden rounded-3xl">
      {event.posterUrl ? (
        <img src={event.posterUrl} alt={event.title} className="h-44 w-full object-cover" />
      ) : (
        <div className="flex h-44 items-center justify-center bg-hero-mesh text-center text-white">
          <div>
            <p className="text-xs uppercase tracking-[0.4em] text-white/60">{event.categoryName}</p>
            <h3 className="mt-3 px-6 text-2xl font-bold">{event.title}</h3>
          </div>
        </div>
      )}
      <div className="space-y-4 p-5">
        <div className="flex items-center justify-between gap-3">
          <span className="rounded-full bg-orange-100 px-3 py-1 text-xs font-semibold text-orange-700 dark:bg-orange-500/15 dark:text-orange-300">{event.eventType}</span>
          <span className="text-xs font-medium text-slate-500 dark:text-slate-400">{event.status}</span>
        </div>
        <div>
          <h3 className="text-xl font-bold text-slate-900 dark:text-white">{event.title}</h3>
          <p className="mt-2 line-clamp-3 text-sm text-slate-600 dark:text-slate-300">{event.description}</p>
        </div>
        <div className="grid gap-2 text-sm text-slate-500 dark:text-slate-400">
          <p>{dayjs(event.startDate).format('DD MMM YYYY')} ? {event.startTime}</p>
          <p>{event.venueName || 'Venue TBA'}</p>
          <p>{event.availableSeats} seats left</p>
        </div>
        <div className="flex items-center justify-between gap-3">
          <Link to={`/events/${event.id}`} className="rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white transition hover:bg-slate-700 dark:bg-white dark:text-slate-900">View details</Link>
          {action}
        </div>
      </div>
    </div>
  )
}
