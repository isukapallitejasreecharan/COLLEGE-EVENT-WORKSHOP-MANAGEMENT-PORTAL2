import { Award, CalendarDays, LayoutDashboard, Megaphone, Settings, Sparkles, UserCircle2 } from 'lucide-react'
import { NavLink } from 'react-router-dom'
import { useAuth } from '../hooks/useAuth'

const commonItems = [
  { to: '/dashboard', label: 'Dashboard', icon: LayoutDashboard },
  { to: '/events', label: 'Events', icon: Sparkles },
  { to: '/calendar', label: 'Calendar', icon: CalendarDays },
  { to: '/announcements', label: 'Announcements', icon: Megaphone },
  { to: '/profile', label: 'Profile', icon: UserCircle2 },
]

export default function Sidebar() {
  const { roles } = useAuth()
  const isAdmin = roles.includes('ROLE_ADMIN')
  const isOrganizer = roles.includes('ROLE_ORGANIZER')
  const isVolunteer = roles.includes('ROLE_VOLUNTEER')
  const isParticipant = roles.includes('ROLE_STUDENT') || roles.includes('ROLE_FACULTY')

  const items = [...commonItems]
  if (isParticipant) {
    items.push({ to: '/registrations', label: 'Registrations', icon: Award })
  }
  if (!isVolunteer) {
    items.push({ to: '/certificates', label: 'Certificates', icon: Award })
  }
  if (isOrganizer) {
    items.push({ to: '/organizer', label: 'Organizer Hub', icon: Settings })
  }
  if (isVolunteer) {
    items.push({ to: '/volunteer', label: 'Volunteer Hub', icon: Settings })
  }
  if (isAdmin) {
    items.push({ to: '/admin', label: 'Admin Hub', icon: Settings })
  }

  return (
    <aside className="glass-panel hidden w-72 rounded-[28px] p-6 lg:block">
      <div className="mb-8 bg-hero-mesh rounded-3xl p-6 text-white">
        <p className="text-xs uppercase tracking-[0.4em] text-white/60">Campus SaaS</p>
        <h1 className="mt-3 font-display text-2xl font-bold">Event Portal</h1>
        <p className="mt-2 text-sm text-white/70">Workshops, attendance, certificates, and real-time updates in one place.</p>
      </div>
      <nav className="space-y-2">
        {items.map((item) => {
          const Icon = item.icon
          return (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) => `flex items-center gap-3 rounded-2xl px-4 py-3 text-sm font-semibold transition ${isActive ? 'bg-slate-900 text-white dark:bg-orange-500 dark:text-white' : 'text-slate-600 hover:bg-slate-100 dark:text-slate-300 dark:hover:bg-slate-800'}`}
            >
              <Icon size={18} />
              {item.label}
            </NavLink>
          )
        })}
      </nav>
    </aside>
  )
}
