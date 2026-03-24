import { useState } from 'react'
import api from '../services/api'

export default function VerifyCertificatePage() {
  const [code, setCode] = useState('')
  const [result, setResult] = useState(null)

  const verify = async () => {
    const { data } = await api.get(`/certificates/verify/${code}`)
    setResult(data.data)
  }

  return (
    <div className="glass-panel mx-auto max-w-xl rounded-[28px] p-8">
      <h2 className="text-3xl font-bold">Verify a certificate</h2>
      <div className="mt-6 flex gap-3">
        <input value={code} onChange={(e) => setCode(e.target.value)} className="flex-1 rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" placeholder="Enter verification code" />
        <button onClick={verify} className="rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Verify</button>
      </div>
      {result && (
        <div className="mt-6 rounded-2xl bg-slate-50 p-5 dark:bg-slate-800">
          <p className="font-semibold">{result.eventTitle}</p>
          <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">Issued to {result.recipientName}</p>
        </div>
      )}
    </div>
  )
}
