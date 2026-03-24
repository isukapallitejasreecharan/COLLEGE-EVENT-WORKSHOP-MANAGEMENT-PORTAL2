import { useEffect, useState } from 'react'
import { BarChart, Bar, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis, PieChart, Pie, Cell } from 'recharts'
import LoadingSpinner from '../components/LoadingSpinner'
import StatCard from '../components/StatCard'
import EventCard from '../components/EventCard'
import { useAuth } from '../hooks/useAuth'
import { dashboardService } from '../services/dashboardService'
import { eventService } from '../services/eventService'

export default function DashboardPage() {
  const { roles } = useAuth()
  const [adminData, setAdminData] = useState(null)
  const [registrations, setRegistrations] = useState([])
  const [certificates, setCertificates] = useState([])
  const [events, setEvents] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const isAdmin = roles.includes('ROLE_ADMIN')
    const isParticipant = roles.includes('ROLE_STUDENT') || roles.includes('ROLE_FACULTY')
    const canViewCertificates = !roles.includes('ROLE_VOLUNTEER')
    Promise.all([
      isAdmin ? dashboardService.admin() : Promise.resolve(null),
      eventService.list({ page: 0, size: 3, direction: 'ASC' }),
      canViewCertificates ? dashboardService.myCertificates().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } }),
      isParticipant ? eventService.myRegistrations().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } }),
    ]).then(([adminResponse, eventsResponse, certificateResponse, registrationsResponse]) => {
      setAdminData(adminResponse?.data?.data || null)
      setEvents(eventsResponse.data.data.content || [])
      setCertificates(certificateResponse.data.data || [])
      setRegistrations(registrationsResponse.data.data || [])
    }).finally(() => setLoading(false))
  }, [roles])

  if (loading) return <LoadingSpinner label="Preparing your dashboard..." />

  const categoryChart = adminData ? Object.entries(adminData.eventsByCategory || {}).map(([name, value]) => ({ name, value })) : []
  const departmentChart = adminData ? Object.entries(adminData.registrationsByDepartment || {}).map(([name, value]) => ({ name, value })) : []

  return (
    <div className="space-y-6">
      <div className="rounded-[32px] bg-hero-mesh p-8 text-white shadow-soft">
        <p className="text-sm uppercase tracking-[0.4em] text-white/60">Portal overview</p>
        <h2 className="mt-3 font-display text-4xl font-bold">Everything happening on campus, in one view.</h2>
        <p className="mt-3 max-w-2xl text-sm text-white/70">Track registrations, coordinate volunteers, issue certificates, and keep students updated in real time.</p>
      </div>
      <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
        <StatCard label="Upcoming events" value={events.length} detail="Fresh workshops and seminars" />
        <StatCard label="My registrations" value={registrations.length} detail="Saved across events" accent="from-emerald-500 to-cyan-500" />
        <StatCard label="Certificates" value={certificates.length} detail="Ready for download" accent="from-fuchsia-500 to-orange-400" />
        <StatCard label="System users" value={adminData?.totalUsers ?? 'Role-based'} detail="Admin metrics appear automatically" accent="from-sky-500 to-indigo-500" />
      </div>
      {adminData && (
        <div className="grid gap-6 xl:grid-cols-2">
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-lg font-bold">Events by category</h3>
            <div className="mt-6 h-80">
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={categoryChart}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="name" />
                  <YAxis />
                  <Tooltip />
                  <Bar dataKey="value" fill="#f97316" radius={[10, 10, 0, 0]} />
                </BarChart>
              </ResponsiveContainer>
            </div>
          </div>
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-lg font-bold">Participation by department</h3>
            <div className="mt-6 h-80">
              <ResponsiveContainer width="100%" height="100%">
                <PieChart>
                  <Pie data={departmentChart} dataKey="value" nameKey="name" outerRadius={110} innerRadius={64} paddingAngle={3}>
                    {departmentChart.map((entry, index) => <Cell key={entry.name} fill={['#f97316', '#10b981', '#3b82f6', '#8b5cf6'][index % 4]} />)}
                  </Pie>
                  <Tooltip />
                </PieChart>
              </ResponsiveContainer>
            </div>
          </div>
        </div>
      )}
      <div>
        <h3 className="mb-4 text-xl font-bold">Featured events</h3>
        <div className="grid gap-4 xl:grid-cols-3">
          {events.map((event) => <EventCard key={event.id} event={event} />)}
        </div>
      </div>
    </div>
  )
}
