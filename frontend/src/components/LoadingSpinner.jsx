export default function LoadingSpinner({ label = 'Loading...' }) {
  return (
    <div className="flex min-h-[240px] items-center justify-center">
      <div className="flex flex-col items-center gap-4">
        <div className="h-12 w-12 animate-spin rounded-full border-4 border-slate-200 border-t-orange-500 dark:border-slate-700 dark:border-t-orange-400" />
        <p className="text-sm text-slate-500 dark:text-slate-400">{label}</p>
      </div>
    </div>
  )
}
