import { useEffect } from 'react'
import { Client } from '@stomp/stompjs'
import SockJS from 'sockjs-client'

const websocketUrl = import.meta.env.VITE_WS_BASE_URL || '/ws'

export function useNotificationSocket(enabled, username, onMessage) {
  useEffect(() => {
    if (!enabled || !username) {
      return undefined
    }

    const client = new Client({
      webSocketFactory: () => new SockJS(websocketUrl),
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
