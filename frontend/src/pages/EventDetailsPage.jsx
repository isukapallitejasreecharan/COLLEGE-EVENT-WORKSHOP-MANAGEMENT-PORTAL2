import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import dayjs from 'dayjs'
import toast from 'react-hot-toast'
import api from '../services/api'
import { useAuth } from '../hooks/useAuth'
import { eventService } from '../services/eventService'
import LoadingSpinner from '../components/LoadingSpinner'

export default function EventDetailsPage() {
  const { id } = useParams()
  const { roles } = useAuth()
  const [event, setEvent] = useState(null)
  const [comments, setComments] = useState([])
  const [feedback, setFeedback] = useState([])
  const [comment, setComment] = useState('')
  const [rating, setRating] = useState(5)
  const [feedbackComment, setFeedbackComment] = useState('')

  const canSubmitFeedback = roles.includes('ROLE_STUDENT') || roles.includes('ROLE_FACULTY')

  useEffect(() => {
    Promise.all([
      eventService.details(id),
      api.get(`/comments/events/${id}`),
      api.get(`/feedback/events/${id}`),
    ])
      .then(([eventResponse, commentsResponse, feedbackResponse]) => {
        setEvent(eventResponse.data.data)
        setComments(commentsResponse.data.data || [])
        setFeedback(feedbackResponse.data.data || [])
      })
      .catch((error) => {
        toast.error(error.response?.data?.message || 'Unable to load event details')
      })
  }, [id])

  const addComment = async () => {
    if (!comment.trim()) {
      toast.error('Enter a comment first')
      return
    }
    try {
      const { data } = await api.post(`/comments/events/${id}`, { content: comment })
      setComments((current) => [data.data, ...current])
      setComment('')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to add comment')
    }
  }

  const submitFeedback = async () => {
    if (!feedbackComment.trim()) {
      toast.error('Enter feedback before submitting')
      return
    }
    try {
      const { data } = await api.post(`/feedback/events/${id}`, { rating, comment: feedbackComment })
      setFeedback((current) => [data.data, ...current])
      setFeedbackComment('')
      setRating(5)
      toast.success('Feedback submitted')
    } catch (error) {
      toast.error(error.response?.data?.message || 'Unable to submit feedback')
    }
  }

  if (!event) return <LoadingSpinner label="Loading event details..." />

  return (
    <div className="space-y-6">
      <div className="overflow-hidden rounded-[32px] bg-hero-mesh p-8 text-white shadow-soft">
        <p className="text-sm uppercase tracking-[0.4em] text-white/60">{event.categoryName}</p>
        <h2 className="mt-3 font-display text-4xl font-bold">{event.title}</h2>
        <p className="mt-4 max-w-3xl text-sm text-white/75">{event.description}</p>
        <div className="mt-6 flex flex-wrap gap-3 text-sm text-white/80">
          <span>{dayjs(event.startDate).format('DD MMM YYYY')} ? {event.startTime}</span>
          <span>{event.venueName}</span>
          <span>{event.availableSeats} seats available</span>
          <span>Code: {event.eventCode}</span>
        </div>
      </div>
      <div className="grid gap-6 xl:grid-cols-[1.2fr_0.8fr]">
        <div className="space-y-6">
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-xl font-bold">Sessions</h3>
            <div className="mt-4 space-y-3">
              {event.sessions?.map((session, index) => (
                <div key={session.id || index} className="rounded-2xl bg-slate-50 p-4 dark:bg-slate-800">
                  <p className="font-semibold">{session.title}</p>
                  <p className="text-sm text-slate-500 dark:text-slate-400">{session.sessionDate} ? {session.startTime} - {session.endTime}</p>
                  <p className="mt-2 text-sm">{session.description}</p>
                </div>
              ))}
            </div>
          </div>
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-xl font-bold">Discussion</h3>
            <div className="mt-4 flex gap-3">
              <input value={comment} onChange={(e) => setComment(e.target.value)} placeholder="Ask a question or leave a comment" className="flex-1 rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" />
              <button onClick={addComment} className="rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Post</button>
            </div>
            <div className="mt-5 space-y-3">
              {comments.map((item) => (
                <div key={item.id} className="rounded-2xl bg-slate-50 p-4 dark:bg-slate-800">
                  <div className="flex items-center justify-between gap-3">
                    <p className="font-semibold">{item.userName}</p>
                    <span className="text-xs text-slate-500 dark:text-slate-400">{item.createdAt}</span>
                  </div>
                  <p className="mt-2 text-sm">{item.content}</p>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="space-y-6">
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-xl font-bold">Quick facts</h3>
            <div className="mt-4 space-y-3 text-sm text-slate-600 dark:text-slate-300">
              <p>Speakers: {event.speakers?.join(', ') || 'To be announced'}</p>
              <p>Organizer: {event.organizerName}</p>
              <p>Registration deadline: {dayjs(event.registrationDeadline).format('DD MMM YYYY HH:mm')}</p>
              <p>Tags: {event.tags}</p>
            </div>
          </div>
          {canSubmitFeedback && (
            <div className="glass-panel rounded-[28px] p-6">
              <h3 className="text-xl font-bold">Submit feedback</h3>
              <div className="mt-4 space-y-3">
                <select value={rating} onChange={(e) => setRating(Number(e.target.value))} className="w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800">
                  {[5, 4, 3, 2, 1].map((value) => <option key={value} value={value}>{value} / 5</option>)}
                </select>
                <textarea value={feedbackComment} onChange={(e) => setFeedbackComment(e.target.value)} placeholder="Share what worked well and what could improve" className="h-28 w-full rounded-2xl border border-slate-200 px-4 py-3 dark:border-slate-700 dark:bg-slate-800" />
                <button onClick={submitFeedback} className="w-full rounded-2xl bg-slate-900 px-4 py-3 font-semibold text-white dark:bg-orange-500">Submit feedback</button>
              </div>
            </div>
          )}
          <div className="glass-panel rounded-[28px] p-6">
            <h3 className="text-xl font-bold">Feedback snapshot</h3>
            <div className="mt-4 space-y-3">
              {feedback.map((item) => (
                <div key={item.id} className="rounded-2xl bg-slate-50 p-4 dark:bg-slate-800">
                  <p className="font-semibold">{item.userName} ? {item.rating}/5</p>
                  <p className="mt-2 text-sm text-slate-600 dark:text-slate-300">{item.comment}</p>
                </div>
              ))}
              {!feedback.length && <p className="text-sm text-slate-500 dark:text-slate-400">No feedback yet.</p>}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
