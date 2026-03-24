import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import { eventService } from '../services/eventService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function MyRegistrationsPage() {
  const [items, setItems] = useState(null)

  useEffect(() => {
    eventService.myRegistrations()
      .then(({ data }) => setItems(data.data || []))
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load registrations')
        setItems([])
      })
  }, [])

  if (!items) return <LoadingSpinner label="Loading your registrations..." />

  return (
    <div className="glass-panel rounded-[28px] p-6">
      <h2 className="text-2xl font-bold">My registrations</h2>
      <div className="mt-6 overflow-x-auto">
        <table className="min-w-full text-left text-sm">
          <thead>
            <tr className="text-slate-500 dark:text-slate-400">
              <th className="pb-3">Event</th>
              <th className="pb-3">Status</th>
              <th className="pb-3">Registered</th>
              <th className="pb-3">Bookmark</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.id} className="border-t border-slate-200 dark:border-slate-800">
                <td className="py-4 font-semibold">{item.eventTitle}</td>
                <td className="py-4">{item.status}</td>
                <td className="py-4">{item.registrationDate}</td>
                <td className="py-4">{item.bookmarked ? 'Saved' : 'No'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
