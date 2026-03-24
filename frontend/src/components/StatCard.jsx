import { motion } from 'framer-motion'

export default function StatCard({ label, value, detail, accent = 'from-orange-500 to-emerald-500' }) {
  return (
    <motion.div
      initial={{ opacity: 0, y: 12 }}
      animate={{ opacity: 1, y: 0 }}
      className="gradient-border"
    >
      <div className="p-5">
        <div className={`mb-4 h-1.5 w-20 rounded-full bg-gradient-to-r ${accent}`} />
        <p className="text-sm font-medium text-slate-500 dark:text-slate-400">{label}</p>
        <p className="mt-2 text-3xl font-bold text-slate-900 dark:text-white">{value}</p>
        <p className="mt-2 text-sm text-slate-500 dark:text-slate-400">{detail}</p>
      </div>
    </motion.div>
  )
}
