import { Outlet } from 'react-router-dom'
import Sidebar from '../components/Sidebar'
import Topbar from '../components/Topbar'

export default function AppLayout() {
  return (
    <div className="app-shell min-h-screen px-4 py-4 lg:px-6">
      <div className="mx-auto grid max-w-[1600px] gap-6 lg:grid-cols-[288px_1fr]">
        <Sidebar />
        <div className="space-y-6">
          <Topbar />
          <main className="space-y-6">
            <Outlet />
          </main>
        </div>
      </div>
    </div>
  )
}
