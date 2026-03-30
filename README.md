# College Event & Workshop Management Portal

A monorepo full-stack application for managing college workshops, seminars, hackathons, attendance, certificates, notifications, and role-based operations across admin, organizer, student, faculty, and volunteer users.

## Monorepo layout

```text
backend/   Spring Boot API, auth, scheduling, websocket notifications, PDF certificates, uploads
frontend/  React + Vite + Tailwind dashboard application
database/  MySQL schema and seed SQL
docs/      Postman collection and supporting docs
```

## What is included

### Backend
- Java 17 + Spring Boot 3.3.x
- Spring Security with JWT access and refresh tokens
- Role-based access control for `ADMIN`, `ORGANIZER`, `STUDENT`, `FACULTY`, `VOLUNTEER`
- Spring Data JPA / Hibernate layered architecture
- MapStruct DTO mapping
- Swagger / OpenAPI at `/api/swagger-ui.html`
- Global exception handling and validation
- Actuator health / metrics endpoints
- Event management, registrations, attendance, announcements, feedback, comments
- File uploads for avatars, posters, and materials
- PDF certificate generation with QR verification codes
- Email service integration with graceful fallback logging in dev
- WebSocket notifications via STOMP/SockJS
- Scheduler-based reminder job
- In-memory auth rate limiting for `/api/auth/**`
- Dev profile that runs immediately with H2 in MySQL compatibility mode
- Prod profile for MySQL + optional Redis cache support

### Frontend
- React + Vite
- Tailwind CSS responsive SaaS-style UI
- Auth flows, route protection, dashboards, organizer/admin/volunteer hubs
- Event browsing, event details, comments, certificates, profile, announcements, calendar
- Recharts analytics widgets
- Framer Motion stat cards
- Light/dark mode toggle
- Toast notifications

## Quick start

### Backend

```bash
cd backend
mvn spring-boot:run -Dmaven.repo.local=../.m2repo
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server runs on `http://localhost:5173` and proxies `/api` and `/ws` to the backend.

## Demo accounts

- Admin: `admin@collegeportal.local` / `Admin@123`
- Organizer: `organizer@collegeportal.local` / `Organizer@123`
- Student: `student@collegeportal.local` / `Student@123`
- Faculty: `faculty@collegeportal.local` / `Faculty@123`
- Volunteer: `volunteer@collegeportal.local` / `Volunteer@123`

## MySQL setup

- Schema: [database/schema.sql](/D:/college-event-portal/database/schema.sql)
- Seed data: [database/seed.sql](/D:/college-event-portal/database/seed.sql)

## Postman

Import the collection at [docs/postman/College-Event-Portal.postman_collection.json](/D:/college-event-portal/docs/postman/College-Event-Portal.postman_collection.json).
