import { useEffect, useState } from 'react'
import { useSearchParams } from 'react-router-dom'
import { authService } from '../services/authService'

export default function VerifyEmailPage() {
  const [params] = useSearchParams()
  const [status, setStatus] = useState('Verifying your account...')

  useEffect(() => {
    const token = params.get('token')
    if (!token) {
      setStatus('Verification token is missing.')
      return
    }
    authService.verifyEmail(token)
      .then(() => setStatus('Your email has been verified successfully.'))
      .catch(() => setStatus('Verification failed or the token expired.'))
  }, [params])

  return <div className="glass-panel rounded-3xl p-8 text-center text-lg font-semibold">{status}</div>
}
