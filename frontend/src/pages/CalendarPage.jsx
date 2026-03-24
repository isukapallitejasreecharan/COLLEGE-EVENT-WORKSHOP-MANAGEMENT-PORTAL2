import { useEffect, useState } from 'react'
import dayjs from 'dayjs'
import { eventService } from '../services/eventService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function CalendarPage() {
  const [items, setItems] = useState(null)

  useEffect(() => {
    const from = dayjs().startOf('month').format('YYYY-MM-DD')
    const to = dayjs().endOf('month').format('YYYY-MM-DD')
    eventService.calendar({ from, to }).then(({ data }) => setItems(data.data || []))
  }, [])

  if (!items) return <LoadingSpinner label="Loading calendar..." />

  return (
    <div className="glass-panel rounded-[28px] p-6">
      <h2 className="text-2xl font-bold">Monthly event calendar</h2>
      <div className="mt-6 grid gap-4 md:grid-cols-2 xl:grid-cols-3">
        {items.map((item) => (
          <div key={item.id} className="rounded-2xl bg-slate-50 p-4 dark:bg-slate-800">
            <p className="text-xs uppercase tracking-[0.35em] text-orange-500">{dayjs(item.startDate).format('ddd')}</p>
            <h3 className="mt-2 text-lg font-bold">{item.title}</h3>
            <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">{dayjs(item.startDate).format('DD MMM YYYY')} ? {item.startTime}</p>
          </div>
        ))}
      </div>
    </div>
  )
}
