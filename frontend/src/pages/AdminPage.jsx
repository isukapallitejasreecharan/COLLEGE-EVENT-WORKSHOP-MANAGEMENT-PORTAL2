import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import StatCard from '../components/StatCard'
import LoadingSpinner from '../components/LoadingSpinner'
import { dashboardService } from '../services/dashboardService'
import api from '../services/api'

export default function AdminPage() {
  const [stats, setStats] = useState(null)
  const [audit, setAudit] = useState([])

  useEffect(() => {
    Promise.all([
      dashboardService.admin(),
      api.get('/admin/audit-logs'),
    ])
      .then(([statsResponse, auditResponse]) => {
        setStats(statsResponse.data.data)
        setAudit(auditResponse.data.data.content || [])
      })
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load admin data')
      })
  }, [])

  if (!stats) return <LoadingSpinner label="Loading admin hub..." />

  return (
    <div className="space-y-6">
      <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-5">
        <StatCard label="Users" value={stats.totalUsers} detail="Active accounts" />
        <StatCard label="Events" value={stats.totalEvents} detail="Across the portal" accent="from-emerald-500 to-cyan-500" />
        <StatCard label="Registrations" value={stats.totalRegistrations} detail="Participation volume" accent="from-sky-500 to-indigo-500" />
        <StatCard label="Certificates" value={stats.totalCertificates} detail="Issued records" accent="from-fuchsia-500 to-orange-400" />
        <StatCard label="Announcements" value={stats.activeAnnouncements} detail="Currently active" accent="from-rose-500 to-orange-500" />
      </div>
      <div className="glass-panel rounded-[28px] p-6">
        <h2 className="text-2xl font-bold">Recent audit logs</h2>
        <div className="mt-5 space-y-3">
          {audit.map((log) => (
            <div key={log.id} className="rounded-2xl bg-slate-50 p-4 text-sm dark:bg-slate-800">
              <p className="font-semibold">{log.action} - {log.entityType}</p>
              <p className="mt-1 text-slate-500 dark:text-slate-400">{log.details}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}
