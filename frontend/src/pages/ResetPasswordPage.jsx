import { useSearchParams } from 'react-router-dom'
import { useState } from 'react'
import toast from 'react-hot-toast'
import { authService } from '../services/authService'

export default function ResetPasswordPage() {
  const [params] = useSearchParams()
  const [form, setForm] = useState({ email: '', token: params.get('token') || '', password: '' })

  const sendReset = async () => {
    await authService.forgotPassword({ email: form.email })
    toast.success('Reset link sent')
  }

  const resetPassword = async () => {
    await authService.resetPassword({ token: form.token, password: form.password })
    toast.success('Password updated')
  }

  return (
    <div className="space-y-6">
      <div>
        <h2 className="font-display text-3xl font-bold">Reset password</h2>
        <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">Request a reset link or set a new password with your token.</p>
      </div>
      <div className="space-y-3 rounded-3xl bg-slate-50 p-4 dark:bg-slate-800">
        <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-900" placeholder="Email" onChange={(e) => setForm({ ...form, email: e.target.value })} />
        <button onClick={sendReset} className="rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Send reset link</button>
      </div>
      <div className="space-y-3 rounded-3xl bg-slate-50 p-4 dark:bg-slate-800">
        <input className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-900" placeholder="Token" value={form.token} onChange={(e) => setForm({ ...form, token: e.target.value })} />
        <input type="password" className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-900" placeholder="New password" onChange={(e) => setForm({ ...form, password: e.target.value })} />
        <button onClick={resetPassword} className="rounded-2xl bg-emerald-600 px-4 py-3 font-semibold text-white">Set new password</button>
      </div>
    </div>
  )
}
