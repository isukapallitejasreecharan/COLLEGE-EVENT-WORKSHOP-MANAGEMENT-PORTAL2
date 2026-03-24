import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import toast from 'react-hot-toast'
import { useAuth } from '../hooks/useAuth'

export default function LoginPage() {
  const navigate = useNavigate()
  const { login } = useAuth()
  const [form, setForm] = useState({ email: 'admin@collegeportal.local', password: 'Admin@123', rememberMe: true })
  const [submitting, setSubmitting] = useState(false)

  const handleSubmit = async (event) => {
    event.preventDefault()
    setSubmitting(true)
    try {
      const data = await login(form)
      const role = data.roles?.[0]
      if (role === 'ROLE_ADMIN') navigate('/admin')
      else if (role === 'ROLE_ORGANIZER') navigate('/organizer')
      else if (role === 'ROLE_VOLUNTEER') navigate('/volunteer')
      else navigate('/dashboard')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to sign in')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <div>
      <p className="text-sm font-semibold uppercase tracking-[0.4em] text-orange-500">Welcome back</p>
      <h2 className="mt-4 font-display text-3xl font-bold">Sign in to your workspace</h2>
      <form onSubmit={handleSubmit} className="mt-8 space-y-4">
        <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} placeholder="Email" />
        <input type="password" className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} placeholder="Password" />
        <label className="flex items-center gap-2 text-sm text-slate-500 dark:text-slate-400">
          <input type="checkbox" checked={form.rememberMe} onChange={(e) => setForm({ ...form, rememberMe: e.target.checked })} />
          Keep me signed in
        </label>
        <button disabled={submitting} className="w-full rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">{submitting ? 'Signing in...' : 'Sign in'}</button>
      </form>
      <div className="mt-6 flex items-center justify-between text-sm text-slate-500 dark:text-slate-400">
        <Link to="/register">Create account</Link>
        <Link to="/reset-password">Reset password</Link>
      </div>
    </div>
  )
}
