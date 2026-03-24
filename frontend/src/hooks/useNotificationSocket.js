import { useEffect } from 'react'
import { Client } from '@stomp/stompjs'
import SockJS from 'sockjs-client'

export function useNotificationSocket(enabled, username, onMessage) {
  useEffect(() => {
    if (!enabled || !username) {
      return undefined
    }

    const client = new Client({
      webSocketFactory: () => new SockJS('/ws'),
      reconnectDelay: 5000,
      onConnect: () => {
        client.subscribe(`/user/${username}/queue/notifications`, (message) => {
          onMessage?.(JSON.parse(message.body))
        })
      },
    })

    client.activate()
    return () => client.deactivate()
  }, [enabled, username, onMessage])
}
