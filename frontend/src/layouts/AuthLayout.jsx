import { Outlet } from 'react-router-dom'

export default function AuthLayout() {
  return (
    <div className="min-h-screen bg-hero-mesh px-4 py-10 text-white">
      <div className="mx-auto grid max-w-6xl gap-8 lg:grid-cols-[1.2fr_0.8fr]">
        <div className="rounded-[32px] border border-white/10 bg-white/5 p-10 backdrop-blur-xl">
          <p className="text-sm uppercase tracking-[0.4em] text-white/60">College Event & Workshop Management Portal</p>
          <h1 className="mt-6 max-w-xl font-display text-5xl font-bold leading-tight">Launch engaging campus experiences with one connected platform.</h1>
          <div className="mt-10 grid gap-4 sm:grid-cols-3">
            <div className="rounded-3xl bg-white/10 p-4">
              <p className="text-sm text-white/60">Real-time notifications</p>
              <p className="mt-3 text-2xl font-bold">WebSocket</p>
            </div>
            <div className="rounded-3xl bg-white/10 p-4">
              <p className="text-sm text-white/60">Certificates</p>
              <p className="mt-3 text-2xl font-bold">PDF + QR</p>
            </div>
            <div className="rounded-3xl bg-white/10 p-4">
              <p className="text-sm text-white/60">Access model</p>
              <p className="mt-3 text-2xl font-bold">RBAC</p>
            </div>
          </div>
        </div>
        <div className="rounded-[32px] bg-white p-6 text-slate-900 shadow-soft dark:bg-slate-900 dark:text-white">
          <Outlet />
        </div>
      </div>
    </div>
  )
}
