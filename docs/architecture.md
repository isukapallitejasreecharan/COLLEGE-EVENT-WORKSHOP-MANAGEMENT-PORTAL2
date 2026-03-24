# Architecture Notes

## Backend layering
- Controllers expose REST endpoints and enforce role boundaries.
- Services contain business workflows for auth, events, registrations, attendance, certificates, announcements, and admin reporting.
- Repositories handle persistence through Spring Data JPA.
- DTOs + MapStruct isolate transport models from entities.

## Runtime integration
- JWT secures all authenticated API modules.
- STOMP/SockJS enables real-time notification delivery.
- Scheduler sends reminder notifications for upcoming events.
- Local file storage backs uploads and generated certificate PDFs.
- Dev profile uses H2 for zero-friction startup; prod profile targets MySQL and optional Redis cache.
