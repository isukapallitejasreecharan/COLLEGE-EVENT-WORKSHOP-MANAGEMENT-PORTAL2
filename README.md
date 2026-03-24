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

Notes:
- The default `dev` profile uses H2 so the backend starts immediately with seed data.
- API base path is `http://localhost:8080/api`.
- Swagger UI is `http://localhost:8080/api/swagger-ui.html`.
- H2 console is `http://localhost:8080/api/h2-console`.
- If port `8080` is already in use, run `mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081`.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server runs on `http://localhost:5173` and proxies `/api` and `/ws` to the backend.

## Demo accounts

These users are seeded automatically by the Spring Boot dev profile and are also represented in `database/seed.sql`.

- Admin: `admin@collegeportal.local` / `Admin@123`
- Organizer: `organizer@collegeportal.local` / `Organizer@123`
- Student: `student@collegeportal.local` / `Student@123`
- Faculty: `faculty@collegeportal.local` / `Faculty@123`
- Volunteer: `volunteer@collegeportal.local` / `Volunteer@123`

## MySQL setup

The project ships with production-ready MySQL scripts:
- Schema: [database/schema.sql](/D:/TejaSpring/database/schema.sql)
- Seed data: [database/seed.sql](/D:/TejaSpring/database/seed.sql)

To run against MySQL instead of H2:

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p < database/seed.sql
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments=--DB_HOST=localhost --DB_PORT=3306 --DB_NAME=college_event_portal --DB_USERNAME=root --DB_PASSWORD=your_password
```

Environment variables supported by the backend:
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
- `JWT_SECRET`
- `FRONTEND_URL`
- `UPLOADS_DIR`
- `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `MAIL_AUTH`, `MAIL_STARTTLS`
- `REDIS_HOST`, `REDIS_PORT`

## Key API modules

- `/api/auth`
- `/api/users`
- `/api/events`
- `/api/registrations`
- `/api/attendance`
- `/api/certificates`
- `/api/notifications`
- `/api/admin`
- `/api/feedback`
- `/api/comments`
- `/api/announcements`
- `/api/files/public/{filename}`

## Postman

Import the collection at [docs/postman/College-Event-Portal.postman_collection.json](/D:/TejaSpring/docs/postman/College-Event-Portal.postman_collection.json).

## Verification notes

Completed locally:
- Backend Maven compile succeeded.
- Frontend production build succeeded.
- Backend runtime sanity check on alternate port succeeded.
- Login and event listing APIs returned valid responses.

Important note:
- The backend defaults to H2 in `dev` so `mvn spring-boot:run` works out of the box.
- MySQL is fully supported via the `prod` profile and the provided SQL scripts.

## Useful paths

- Backend main app: [backend/src/main/java/com/collegeportal/CollegePortalApplication.java](/D:/TejaSpring/backend/src/main/java/com/collegeportal/CollegePortalApplication.java)
- Backend config: [backend/src/main/resources/application.yml](/D:/TejaSpring/backend/src/main/resources/application.yml)
- Frontend router: [frontend/src/routes/AppRouter.jsx](/D:/TejaSpring/frontend/src/routes/AppRouter.jsx)
- Frontend auth context: [frontend/src/contexts/AuthContext.jsx](/D:/TejaSpring/frontend/src/contexts/AuthContext.jsx)
