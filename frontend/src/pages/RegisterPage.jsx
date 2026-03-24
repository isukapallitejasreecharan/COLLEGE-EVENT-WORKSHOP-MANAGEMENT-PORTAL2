import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import toast from 'react-hot-toast'
import { useAuth } from '../hooks/useAuth'

export default function RegisterPage() {
  const navigate = useNavigate()
  const { register } = useAuth()
  const [form, setForm] = useState({ firstName: '', lastName: '', email: '', password: '', role: 'STUDENT' })

  const handleSubmit = async (event) => {
    event.preventDefault()
    try {
      await register(form)
      navigate('/dashboard')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to register')
    }
  }

  return (
    <div>
      <p className="text-sm font-semibold uppercase tracking-[0.4em] text-emerald-500">Get started</p>
      <h2 className="mt-4 font-display text-3xl font-bold">Create your portal account</h2>
      <form onSubmit={handleSubmit} className="mt-8 grid gap-4">
        <div className="grid gap-4 sm:grid-cols-2">
          <input className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="First name" onChange={(e) => setForm({ ...form, firstName: e.target.value })} />
          <input className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Last name" onChange={(e) => setForm({ ...form, lastName: e.target.value })} />
        </div>
        <input className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Email" onChange={(e) => setForm({ ...form, email: e.target.value })} />
        <input type="password" className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Password" onChange={(e) => setForm({ ...form, password: e.target.value })} />
        <select className="rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" value={form.role} onChange={(e) => setForm({ ...form, role: e.target.value })}>
          <option value="STUDENT">Student</option>
          <option value="FACULTY">Faculty</option>
          <option value="VOLUNTEER">Volunteer</option>
        </select>
        <button className="rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Create account</button>
      </form>
      <p className="mt-6 text-sm text-slate-500 dark:text-slate-400">Already have an account? <Link className="text-orange-500" to="/login">Sign in</Link></p>
    </div>
  )
}
