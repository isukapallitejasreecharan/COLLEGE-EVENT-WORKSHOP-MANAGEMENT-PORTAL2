import { Moon, Sun } from 'lucide-react'
import { useTheme } from '../hooks/useTheme'

export default function ThemeToggle() {
  const { theme, toggleTheme } = useTheme()

  return (
    <button
      onClick={toggleTheme}
      className="rounded-full border border-slate-200 bg-white p-2.5 text-slate-600 transition hover:border-orange-300 hover:text-orange-500 dark:border-slate-700 dark:bg-slate-900 dark:text-slate-300"
      type="button"
    >
      {theme === 'dark' ? <Sun size={18} /> : <Moon size={18} />}
    </button>
  )
}
