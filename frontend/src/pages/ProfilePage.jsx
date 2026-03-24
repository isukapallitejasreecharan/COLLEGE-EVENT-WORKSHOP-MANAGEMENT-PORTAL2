import { useEffect, useMemo, useRef, useState } from 'react'
import { motion } from 'framer-motion'
import dayjs from 'dayjs'
import {
  Award,
  BadgeCheck,
  BookUser,
  Building2,
  Camera,
  Clock3,
  IdCard,
  Mail,
  Phone,
  ShieldCheck,
  Sparkles,
  UserCircle2,
} from 'lucide-react'
import toast from 'react-hot-toast'
import StatCard from '../components/StatCard'
import LoadingSpinner from '../components/LoadingSpinner'
import { useAuth } from '../hooks/useAuth'
import { dashboardService } from '../services/dashboardService'
import { eventService } from '../services/eventService'
import api from '../services/api'

function Badge({ children, tone = 'default' }) {
  const styles = {
    default: 'bg-white/70 text-slate-700 dark:bg-slate-800/80 dark:text-slate-200',
    success: 'bg-emerald-100 text-emerald-700 dark:bg-emerald-500/15 dark:text-emerald-300',
    warm: 'bg-orange-100 text-orange-700 dark:bg-orange-500/15 dark:text-orange-300',
    muted: 'bg-slate-100 text-slate-600 dark:bg-slate-800 dark:text-slate-300',
  }

  return <span className={`rounded-full px-3 py-1 text-xs font-semibold ${styles[tone]}`}>{children}</span>
}

function SectionCard({ title, eyebrow, children }) {
  return (
    <div className="glass-panel rounded-[28px] p-6">
      {eyebrow && <p className="text-xs uppercase tracking-[0.35em] text-orange-500">{eyebrow}</p>}
      <h3 className="mt-2 text-xl font-bold text-slate-900 dark:text-white">{title}</h3>
      <div className="mt-5">{children}</div>
    </div>
  )
}

function Avatar({ profile }) {
  if (profile?.profileImageUrl) {
    return <img src={profile.profileImageUrl} alt={profile.fullName} className="h-28 w-28 rounded-[28px] object-cover shadow-soft" />
  }

  return (
    <div className="flex h-28 w-28 items-center justify-center rounded-[28px] bg-white/20 text-4xl font-bold text-white shadow-soft backdrop-blur">
      {profile?.firstName?.[0]}{profile?.lastName?.[0]}
    </div>
  )
}

const initialForm = {
  firstName: '',
  lastName: '',
  phone: '',
  bio: '',
}

export default function ProfilePage() {
  const { user, roles, setSessionUser } = useAuth()
  const fileInputRef = useRef(null)
  const [profile, setProfile] = useState(user)
  const [form, setForm] = useState(initialForm)
  const [notifications, setNotifications] = useState([])
  const [registrations, setRegistrations] = useState([])
  const [certificates, setCertificates] = useState([])
  const [managedEvents, setManagedEvents] = useState([])
  const [assignedEvents, setAssignedEvents] = useState([])
  const [loading, setLoading] = useState(true)
  const [saving, setSaving] = useState(false)
  const [uploading, setUploading] = useState(false)

  const isParticipant = roles.includes('ROLE_STUDENT') || roles.includes('ROLE_FACULTY')
  const canViewCertificates = !roles.includes('ROLE_VOLUNTEER')
  const canManageEvents = roles.includes('ROLE_ADMIN') || roles.includes('ROLE_ORGANIZER')
  const isVolunteer = roles.includes('ROLE_VOLUNTEER')

  useEffect(() => {
    const loadProfile = async () => {
      setLoading(true)
      try {
        const profileRequest = api.get('/users/me')
        const notificationsRequest = dashboardService.notifications().catch(() => ({ data: { data: [] } }))
        const registrationsRequest = isParticipant ? eventService.myRegistrations().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } })
        const certificatesRequest = canViewCertificates ? dashboardService.myCertificates().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } })
        const managedEventsRequest = canManageEvents ? eventService.mine().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } })
        const assignedEventsRequest = isVolunteer ? eventService.assignedVolunteer().catch(() => ({ data: { data: [] } })) : Promise.resolve({ data: { data: [] } })

        const [profileResponse, notificationsResponse, registrationsResponse, certificatesResponse, managedEventsResponse, assignedEventsResponse] = await Promise.all([
          profileRequest,
          notificationsRequest,
          registrationsRequest,
          certificatesRequest,
          managedEventsRequest,
          assignedEventsRequest,
        ])

        const nextProfile = profileResponse.data.data
        setProfile(nextProfile)
        setSessionUser(nextProfile)
        setForm({
          firstName: nextProfile.firstName || '',
          lastName: nextProfile.lastName || '',
          phone: nextProfile.phone || '',
          bio: nextProfile.bio || '',
        })
        setNotifications(notificationsResponse.data.data || [])
        setRegistrations(registrationsResponse.data.data || [])
        setCertificates(certificatesResponse.data.data || [])
        setManagedEvents(managedEventsResponse.data.data || [])
        setAssignedEvents(assignedEventsResponse.data.data || [])
      } catch (error) {
        toast.error(error.response?.data?.message || 'Unable to load your profile')
      } finally {
        setLoading(false)
      }
    }

    loadProfile()
  }, [canManageEvents, canViewCertificates, isParticipant, isVolunteer, setSessionUser])

  const completion = useMemo(() => {
    const checkpoints = [profile?.firstName, profile?.lastName, profile?.phone, profile?.bio, profile?.profileImageUrl]
    const completed = checkpoints.filter((item) => item && String(item).trim()).length
    return Math.round((completed / checkpoints.length) * 100)
  }, [profile])

  const headline = useMemo(() => {
    if (roles.includes('ROLE_ADMIN')) return 'Platform command center'
    if (roles.includes('ROLE_ORGANIZER')) return 'Event architect'
    if (roles.includes('ROLE_VOLUNTEER')) return 'On-ground operations crew'
    if (roles.includes('ROLE_FACULTY')) return 'Faculty participant'
    return 'Student builder profile'
  }, [roles])

  const primaryIdLabel = profile?.studentId ? 'Student ID' : profile?.employeeId ? 'Employee ID' : 'Portal ID'
  const primaryIdValue = profile?.studentId || profile?.employeeId || `USER-${profile?.id}`

  const stats = [
    {
      label: 'Profile strength',
      value: `${completion}%`,
      detail: 'Complete identity and bio fields for a richer campus presence.',
      accent: 'from-orange-500 to-amber-400',
    },
    {
      label: 'Notifications',
      value: notifications.length,
      detail: 'Latest reminders, confirmations, and announcements.',
      accent: 'from-sky-500 to-indigo-500',
    },
    {
      label: canManageEvents ? 'Managed events' : isVolunteer ? 'Assigned events' : 'Registrations',
      value: canManageEvents ? managedEvents.length : isVolunteer ? assignedEvents.length : registrations.length,
      detail: canManageEvents ? 'Events you can coordinate and publish.' : isVolunteer ? 'Sessions where you are helping onsite.' : 'Events currently linked to your account.',
      accent: 'from-emerald-500 to-cyan-500',
    },
    {
      label: 'Certificates',
      value: certificates.length,
      detail: canViewCertificates ? 'Issued records ready to revisit or download.' : 'Volunteer accounts do not receive certificate access.',
      accent: 'from-fuchsia-500 to-rose-400',
    },
  ]

  const roleBadges = (profile?.roles || []).map((role) => role.replace('ROLE_', '').replace('_', ' '))

  const handleSave = async () => {
    setSaving(true)
    try {
      const { data } = await api.patch('/users/me', form)
      setProfile(data.data)
      setSessionUser(data.data)
      toast.success('Profile updated')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to update profile')
    } finally {
      setSaving(false)
    }
  }

  const handleAvatarSelect = async (event) => {
    const file = event.target.files?.[0]
    if (!file) return

    const formData = new FormData()
    formData.append('file', file)

    setUploading(true)
    try {
      await api.post('/users/me/avatar', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      })
      const { data } = await api.get('/users/me')
      setProfile(data.data)
      setSessionUser(data.data)
      toast.success('Profile photo updated')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to upload profile photo')
    } finally {
      setUploading(false)
      if (fileInputRef.current) fileInputRef.current.value = ''
    }
  }

  if (loading && !profile) return <LoadingSpinner label="Loading your profile hub..." />

  return (
    <div className="space-y-6">
      <motion.section initial={{ opacity: 0, y: 16 }} animate={{ opacity: 1, y: 0 }} className="overflow-hidden rounded-[34px] bg-hero-mesh p-8 text-white shadow-soft">
        <div className="grid gap-8 xl:grid-cols-[1.15fr_0.85fr]">
          <div className="flex flex-col gap-6 md:flex-row md:items-start">
            <div className="relative shrink-0">
              <Avatar profile={profile} />
              <button
                type="button"
                onClick={() => fileInputRef.current?.click()}
                className="absolute -bottom-3 -right-3 rounded-2xl border border-white/30 bg-slate-950/80 p-3 text-white backdrop-blur transition hover:bg-slate-950"
              >
                <Camera size={18} />
              </button>
              <input ref={fileInputRef} type="file" accept="image/*" className="hidden" onChange={handleAvatarSelect} />
            </div>
            <div className="space-y-4">
              <div>
                <p className="text-sm uppercase tracking-[0.4em] text-white/60">Profile studio</p>
                <h1 className="mt-3 font-display text-4xl font-bold">{profile?.fullName}</h1>
                <p className="mt-3 max-w-2xl text-sm text-white/75">{headline}. Shape how you appear across the portal, keep your contact details fresh, and track how active your account is.</p>
              </div>
              <div className="flex flex-wrap gap-2">
                {roleBadges.map((role) => <Badge key={role} tone="warm">{role}</Badge>)}
                <Badge tone={profile?.emailVerified ? 'success' : 'muted'}>{profile?.emailVerified ? 'Email verified' : 'Email pending'}</Badge>
                <Badge tone={profile?.enabled ? 'success' : 'muted'}>{profile?.enabled ? 'Account active' : 'Account limited'}</Badge>
                {uploading && <Badge tone="default">Uploading photo...</Badge>}
              </div>
            </div>
          </div>
          <div className="rounded-[30px] border border-white/15 bg-white/10 p-6 backdrop-blur-xl">
            <div className="flex items-center justify-between gap-3">
              <div>
                <p className="text-xs uppercase tracking-[0.35em] text-white/55">Account readiness</p>
                <p className="mt-2 text-3xl font-bold">{completion}%</p>
              </div>
              <Sparkles className="text-amber-300" size={28} />
            </div>
            <div className="mt-5 h-3 rounded-full bg-white/15">
              <div className="h-3 rounded-full bg-gradient-to-r from-orange-400 via-amber-300 to-emerald-300" style={{ width: `${completion}%` }} />
            </div>
            <div className="mt-6 grid gap-4 sm:grid-cols-2">
              <div>
                <p className="text-xs uppercase tracking-[0.28em] text-white/55">Primary contact</p>
                <p className="mt-2 text-sm font-semibold text-white">{profile?.email}</p>
                <p className="mt-1 text-sm text-white/70">{profile?.phone || 'Add a phone number for smoother event coordination.'}</p>
              </div>
              <div>
                <p className="text-xs uppercase tracking-[0.28em] text-white/55">Last seen</p>
                <p className="mt-2 text-sm font-semibold text-white">{profile?.lastLoginAt ? dayjs(profile.lastLoginAt).format('DD MMM YYYY, hh:mm A') : 'First session pending'}</p>
                <p className="mt-1 text-sm text-white/70">Portal activity updates after each successful sign-in.</p>
              </div>
            </div>
          </div>
        </div>
      </motion.section>

      <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
        {stats.map((item) => (
          <StatCard key={item.label} label={item.label} value={item.value} detail={item.detail} accent={item.accent} />
        ))}
      </div>

      <div className="grid gap-6 xl:grid-cols-[1.05fr_0.95fr]">
        <div className="space-y-6">
          <SectionCard title="Identity and campus access" eyebrow="Essentials">
            <div className="grid gap-4 md:grid-cols-2">
              <div className="rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <div className="flex items-center gap-3 text-slate-900 dark:text-white">
                  <Mail size={18} className="text-orange-500" />
                  <span className="text-sm font-semibold">Email</span>
                </div>
                <p className="mt-3 text-sm text-slate-600 dark:text-slate-300">{profile?.email}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <div className="flex items-center gap-3 text-slate-900 dark:text-white">
                  <Phone size={18} className="text-emerald-500" />
                  <span className="text-sm font-semibold">Phone</span>
                </div>
                <p className="mt-3 text-sm text-slate-600 dark:text-slate-300">{profile?.phone || 'Not added yet'}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <div className="flex items-center gap-3 text-slate-900 dark:text-white">
                  <Building2 size={18} className="text-sky-500" />
                  <span className="text-sm font-semibold">Department</span>
                </div>
                <p className="mt-3 text-sm text-slate-600 dark:text-slate-300">{profile?.departmentName || 'Not assigned yet'}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <div className="flex items-center gap-3 text-slate-900 dark:text-white">
                  <IdCard size={18} className="text-fuchsia-500" />
                  <span className="text-sm font-semibold">{primaryIdLabel}</span>
                </div>
                <p className="mt-3 text-sm text-slate-600 dark:text-slate-300">{primaryIdValue}</p>
              </div>
            </div>
          </SectionCard>

          <SectionCard title="Edit your public profile" eyebrow="Profile form">
            <div className="grid gap-4 md:grid-cols-2">
              <label className="space-y-2">
                <span className="text-sm font-semibold text-slate-700 dark:text-slate-200">First name</span>
                <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.firstName} onChange={(e) => setForm({ ...form, firstName: e.target.value })} placeholder="First name" />
              </label>
              <label className="space-y-2">
                <span className="text-sm font-semibold text-slate-700 dark:text-slate-200">Last name</span>
                <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.lastName} onChange={(e) => setForm({ ...form, lastName: e.target.value })} placeholder="Last name" />
              </label>
              <label className="space-y-2 md:col-span-2">
                <span className="text-sm font-semibold text-slate-700 dark:text-slate-200">Phone number</span>
                <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.phone} onChange={(e) => setForm({ ...form, phone: e.target.value })} placeholder="Phone number" />
              </label>
              <label className="space-y-2 md:col-span-2">
                <span className="text-sm font-semibold text-slate-700 dark:text-slate-200">Bio</span>
                <textarea className="h-36 w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.bio} onChange={(e) => setForm({ ...form, bio: e.target.value })} placeholder="Tell the campus community what you build, organize, teach, or support." />
              </label>
            </div>
            <div className="mt-5 flex flex-wrap items-center gap-3">
              <button onClick={handleSave} disabled={saving} className="rounded-2xl bg-slate-900 px-5 py-3 text-sm font-semibold text-white transition hover:bg-slate-700 disabled:cursor-not-allowed disabled:opacity-70 dark:bg-orange-500 dark:hover:bg-orange-400">
                {saving ? 'Saving changes...' : 'Save profile'}
              </button>
              <button onClick={() => fileInputRef.current?.click()} type="button" className="rounded-2xl border border-slate-200 px-5 py-3 text-sm font-semibold text-slate-700 dark:border-slate-700 dark:text-slate-200">
                {uploading ? 'Uploading photo...' : 'Change profile photo'}
              </button>
            </div>
          </SectionCard>
        </div>

        <div className="space-y-6">
          <SectionCard title="Trust and status" eyebrow="Account state">
            <div className="space-y-4">
              <div className="flex items-start gap-4 rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <ShieldCheck className="mt-1 text-emerald-500" size={20} />
                <div>
                  <p className="font-semibold text-slate-900 dark:text-white">Verification and access</p>
                  <p className="mt-1 text-sm text-slate-600 dark:text-slate-300">{profile?.emailVerified ? 'Your email is verified, so account recovery and event communications are in good shape.' : 'Verify your email to make sure announcements and secure account flows reach you properly.'}</p>
                </div>
              </div>
              <div className="flex items-start gap-4 rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <BadgeCheck className="mt-1 text-orange-500" size={20} />
                <div>
                  <p className="font-semibold text-slate-900 dark:text-white">Role access</p>
                  <p className="mt-1 text-sm text-slate-600 dark:text-slate-300">Your workspace unlocks features based on your active campus role badges shown above.</p>
                </div>
              </div>
              <div className="flex items-start gap-4 rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
                <Clock3 className="mt-1 text-sky-500" size={20} />
                <div>
                  <p className="font-semibold text-slate-900 dark:text-white">Last login</p>
                  <p className="mt-1 text-sm text-slate-600 dark:text-slate-300">{profile?.lastLoginAt ? dayjs(profile.lastLoginAt).format('DD MMM YYYY, hh:mm A') : 'No recent login timestamp yet.'}</p>
                </div>
              </div>
            </div>
          </SectionCard>

          <SectionCard title="About you" eyebrow="Presence">
            <div className="rounded-3xl bg-slate-50 p-5 dark:bg-slate-800/80">
              <div className="flex items-center gap-3 text-slate-900 dark:text-white">
                <BookUser size={18} className="text-orange-500" />
                <p className="font-semibold">Profile summary</p>
              </div>
              <p className="mt-4 text-sm leading-7 text-slate-600 dark:text-slate-300">{profile?.bio || 'Add a bio to help organizers, collaborators, and participants understand your strengths and campus interests.'}</p>
            </div>
          </SectionCard>

          <SectionCard title="Recent activity" eyebrow="Live pulse">
            <div className="space-y-3">
              {notifications.slice(0, 5).map((item) => (
                <div key={item.id} className="rounded-3xl bg-slate-50 p-4 dark:bg-slate-800/80">
                  <div className="flex items-start gap-3">
                    <div className="mt-1 rounded-2xl bg-orange-100 p-2 text-orange-700 dark:bg-orange-500/15 dark:text-orange-300">
                      <Award size={16} />
                    </div>
                    <div>
                      <p className="text-sm font-semibold text-slate-900 dark:text-white">{item.title}</p>
                      <p className="mt-1 text-sm text-slate-600 dark:text-slate-300">{item.message}</p>
                    </div>
                  </div>
                </div>
              ))}
              {!notifications.length && <p className="text-sm text-slate-500 dark:text-slate-400">Your notifications will appear here as the portal activity picks up.</p>}
            </div>
          </SectionCard>

          <SectionCard title="Portal footprint" eyebrow="Snapshot">
            <div className="grid gap-3 sm:grid-cols-2">
              <div className="rounded-3xl bg-slate-50 p-4 dark:bg-slate-800/80">
                <p className="text-xs uppercase tracking-[0.28em] text-slate-400">Registrations</p>
                <p className="mt-3 text-2xl font-bold text-slate-900 dark:text-white">{registrations.length}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-4 dark:bg-slate-800/80">
                <p className="text-xs uppercase tracking-[0.28em] text-slate-400">Certificates</p>
                <p className="mt-3 text-2xl font-bold text-slate-900 dark:text-white">{certificates.length}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-4 dark:bg-slate-800/80">
                <p className="text-xs uppercase tracking-[0.28em] text-slate-400">Managed events</p>
                <p className="mt-3 text-2xl font-bold text-slate-900 dark:text-white">{managedEvents.length}</p>
              </div>
              <div className="rounded-3xl bg-slate-50 p-4 dark:bg-slate-800/80">
                <p className="text-xs uppercase tracking-[0.28em] text-slate-400">Volunteer assignments</p>
                <p className="mt-3 text-2xl font-bold text-slate-900 dark:text-white">{assignedEvents.length}</p>
              </div>
            </div>
          </SectionCard>
        </div>
      </div>
    </div>
  )
}

