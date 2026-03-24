import { useEffect, useState } from 'react'
import { dashboardService } from '../services/dashboardService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function AnnouncementsPage() {
  const [items, setItems] = useState(null)

  useEffect(() => {
    dashboardService.announcements().then(({ data }) => setItems(data.data || []))
  }, [])

  if (!items) return <LoadingSpinner label="Loading announcements..." />

  return (
    <div className="space-y-4">
      {items.map((item) => (
        <div key={item.id} className="glass-panel rounded-[28px] p-6">
          <p className="text-sm uppercase tracking-[0.4em] text-orange-500">Announcement</p>
          <h2 className="mt-3 text-2xl font-bold">{item.title}</h2>
          <p className="mt-3 text-sm text-slate-600 dark:text-slate-300">{item.content}</p>
          <p className="mt-3 text-xs text-slate-500 dark:text-slate-400">By {item.authorName}</p>
        </div>
      ))}
    </div>
  )
}
