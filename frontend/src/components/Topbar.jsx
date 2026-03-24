import { Bell, Search } from 'lucide-react'
import { useEffect, useState } from 'react'
import { dashboardService } from '../services/dashboardService'
import { useAuth } from '../hooks/useAuth'
import ThemeToggle from './ThemeToggle'

function UserAvatar({ user }) {
  if (user?.profileImageUrl) {
    return <img src={user.profileImageUrl} alt={user.fullName} className="h-10 w-10 rounded-full object-cover" />
  }

  return (
    <div className="h-10 w-10 rounded-full bg-orange-100 text-center text-sm font-bold leading-10 text-orange-700 dark:bg-orange-500/15 dark:text-orange-200">
      {user?.firstName?.[0]}{user?.lastName?.[0]}
    </div>
  )
}

export default function Topbar() {
  const { user, logout } = useAuth()
  const [notifications, setNotifications] = useState([])
  const [open, setOpen] = useState(false)

  useEffect(() => {
    dashboardService.notifications().then(({ data }) => setNotifications(data.data || [])).catch(() => {})
  }, [])

  return (
    <header className="glass-panel flex items-center justify-between gap-4 rounded-[28px] p-4">
      <div className="flex flex-1 items-center gap-3 rounded-2xl bg-slate-100 px-4 py-3 dark:bg-slate-800">
        <Search size={18} className="text-slate-400" />
        <span className="text-sm text-slate-400">Search events, speakers, or certificates</span>
      </div>
      <div className="flex items-center gap-3">
        <ThemeToggle />
        <div className="relative">
          <button type="button" onClick={() => setOpen((current) => !current)} className="rounded-full border border-slate-200 bg-white p-2.5 text-slate-600 dark:border-slate-700 dark:bg-slate-900 dark:text-slate-300">
            <Bell size={18} />
          </button>
          {open && (
            <div className="glass-panel absolute right-0 top-14 z-20 w-80 rounded-3xl p-4">
              <h3 className="text-sm font-bold text-slate-900 dark:text-white">Latest notifications</h3>
              <div className="mt-3 space-y-3">
                {notifications.slice(0, 5).map((notification) => (
                  <div key={notification.id} className="rounded-2xl bg-slate-50 p-3 text-sm dark:bg-slate-800">
                    <p className="font-semibold text-slate-900 dark:text-white">{notification.title}</p>
                    <p className="mt-1 text-slate-500 dark:text-slate-400">{notification.message}</p>
                  </div>
                ))}
                {!notifications.length && <p className="text-sm text-slate-500 dark:text-slate-400">No notifications yet.</p>}
              </div>
            </div>
          )}
        </div>
        <div className="flex items-center gap-3 rounded-full border border-slate-200 bg-white px-4 py-2 dark:border-slate-700 dark:bg-slate-900">
          <UserAvatar user={user} />
          <div>
            <p className="text-sm font-semibold text-slate-900 dark:text-white">{user?.fullName}</p>
            <button type="button" onClick={logout} className="text-xs text-slate-500 dark:text-slate-400">Sign out</button>
          </div>
        </div>
      </div>
    </header>
  )
}
