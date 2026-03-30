-- Core schema reference for production hardening/migrations (Flyway recommended)
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT FALSE,
  two_factor_enabled BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  category VARCHAR(100) NOT NULL,
  event_type VARCHAR(100),
  date DATE,
  start_time TIME,
  end_time TIME,
  capacity INT,
  status VARCHAR(50) NOT NULL,
  organizer_id BIGINT REFERENCES users(id),
  venue_id BIGINT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);
