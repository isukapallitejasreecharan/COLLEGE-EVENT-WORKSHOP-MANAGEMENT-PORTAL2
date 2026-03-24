import { useEffect, useState } from 'react'
import toast from 'react-hot-toast'
import { dashboardService } from '../services/dashboardService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function CertificatesPage() {
  const [items, setItems] = useState(null)

  useEffect(() => {
    dashboardService.myCertificates()
      .then(({ data }) => setItems(data.data || []))
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load certificates')
        setItems([])
      })
  }, [])

  if (!items) return <LoadingSpinner label="Loading certificates..." />

  return (
    <div className="grid gap-4 xl:grid-cols-2">
      {items.map((item) => (
        <div key={item.id} className="glass-panel rounded-[28px] p-6">
          <p className="text-sm uppercase tracking-[0.4em] text-orange-500">Certificate</p>
          <h3 className="mt-3 text-2xl font-bold">{item.eventTitle}</h3>
          <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">Issued to {item.recipientName}</p>
          <p className="mt-1 text-sm text-slate-500 dark:text-slate-400">Verification code: {item.verificationCode}</p>
          <a href={item.pdfUrl} target="_blank" rel="noreferrer" className="mt-5 inline-flex rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white dark:bg-orange-500">Download PDF</a>
        </div>
      ))}
      {!items.length && <div className="glass-panel rounded-[28px] p-6">No certificates yet.</div>}
    </div>
  )
}
