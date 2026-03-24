import { Link } from 'react-router-dom'

export default function NotFoundPage() {
  return (
    <div className="glass-panel rounded-[28px] p-8 text-center">
      <h2 className="font-display text-4xl font-bold">Page not found</h2>
      <p className="mt-3 text-slate-500 dark:text-slate-400">The page you requested does not exist.</p>
      <Link to="/dashboard" className="mt-5 inline-flex rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white dark:bg-orange-500">Return to dashboard</Link>
    </div>
  )
}
