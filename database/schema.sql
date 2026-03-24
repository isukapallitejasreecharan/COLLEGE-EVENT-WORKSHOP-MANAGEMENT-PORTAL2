CREATE DATABASE IF NOT EXISTS college_event_portal;
USE college_event_portal;

CREATE TABLE IF NOT EXISTS roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  name VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS departments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  code VARCHAR(20) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL UNIQUE,
  description VARCHAR(500),
  INDEX idx_department_code (code)
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  name VARCHAR(120) NOT NULL UNIQUE,
  description VARCHAR(500),
  color VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS venues (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  name VARCHAR(150) NOT NULL,
  location VARCHAR(300) NOT NULL,
  capacity INT NOT NULL,
  indoor BIT NOT NULL,
  facilities VARCHAR(500),
  INDEX idx_venue_name (name)
);

CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  department_id BIGINT,
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(30),
  bio VARCHAR(1000),
  profile_image_url VARCHAR(255),
  student_id VARCHAR(40),
  employee_id VARCHAR(40),
  enabled BIT NOT NULL,
  email_verified BIT NOT NULL,
  account_non_locked BIT NOT NULL,
  failed_login_attempts INT NOT NULL,
  lock_time DATETIME,
  last_login_at DATETIME,
  remember_me_token VARCHAR(255),
  CONSTRAINT fk_users_department FOREIGN KEY (department_id) REFERENCES departments(id),
  INDEX idx_user_email (email),
  INDEX idx_user_student_id (student_id)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS speakers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  name VARCHAR(120) NOT NULL,
  designation VARCHAR(120),
  organization VARCHAR(120),
  email VARCHAR(120),
  avatar_url VARCHAR(255),
  bio VARCHAR(1500)
);

CREATE TABLE IF NOT EXISTS events (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  category_id BIGINT NOT NULL,
  department_id BIGINT,
  organizer_id BIGINT NOT NULL,
  venue_id BIGINT,
  title VARCHAR(180) NOT NULL,
  description TEXT NOT NULL,
  poster_url VARCHAR(255),
  event_type VARCHAR(80) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  registration_deadline DATETIME NOT NULL,
  maximum_participants INT NOT NULL,
  visibility VARCHAR(30) NOT NULL,
  status VARCHAR(30) NOT NULL,
  multi_day BIT NOT NULL,
  recurring BIT NOT NULL,
  recurring_rule VARCHAR(120),
  resources TEXT,
  tags TEXT,
  faq TEXT,
  sponsors_text TEXT,
  materials_url VARCHAR(255),
  recording_url VARCHAR(255),
  gallery_urls TEXT,
  registration_requires_approval BIT NOT NULL,
  registration_closed BIT NOT NULL,
  event_code VARCHAR(30) NOT NULL UNIQUE,
  CONSTRAINT fk_events_category FOREIGN KEY (category_id) REFERENCES categories(id),
  CONSTRAINT fk_events_department FOREIGN KEY (department_id) REFERENCES departments(id),
  CONSTRAINT fk_events_organizer FOREIGN KEY (organizer_id) REFERENCES users(id),
  CONSTRAINT fk_events_venue FOREIGN KEY (venue_id) REFERENCES venues(id),
  INDEX idx_event_status_date (status, start_date),
  INDEX idx_event_department (department_id),
  INDEX idx_event_category (category_id)
);

CREATE TABLE IF NOT EXISTS event_speakers_link (
  event_id BIGINT NOT NULL,
  speaker_id BIGINT NOT NULL,
  PRIMARY KEY (event_id, speaker_id),
  CONSTRAINT fk_event_speaker_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_event_speaker_speaker FOREIGN KEY (speaker_id) REFERENCES speakers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS event_sessions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  speaker_id BIGINT,
  title VARCHAR(150) NOT NULL,
  description VARCHAR(2000),
  session_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  material_url VARCHAR(255),
  CONSTRAINT fk_event_sessions_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_event_sessions_speaker FOREIGN KEY (speaker_id) REFERENCES speakers(id)
);

CREATE TABLE IF NOT EXISTS event_registrations (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  status VARCHAR(30) NOT NULL,
  bookmarked BIT NOT NULL,
  checked_in BIT NOT NULL,
  registration_date DATETIME NOT NULL,
  cancelled_at DATETIME,
  remarks VARCHAR(500),
  CONSTRAINT fk_reg_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_reg_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  UNIQUE KEY uq_registration_event_user (event_id, user_id),
  INDEX idx_registration_status (status)
);

CREATE TABLE IF NOT EXISTS attendance (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  registration_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  method VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  check_in_time DATETIME NOT NULL,
  late BIT NOT NULL,
  CONSTRAINT fk_attendance_registration FOREIGN KEY (registration_id) REFERENCES event_registrations(id) ON DELETE CASCADE,
  CONSTRAINT fk_attendance_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_attendance_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS certificates (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  certificate_number VARCHAR(60) NOT NULL UNIQUE,
  verification_code VARCHAR(80) NOT NULL UNIQUE,
  pdf_url VARCHAR(255) NOT NULL,
  issued_at DATETIME NOT NULL,
  emailed BIT NOT NULL,
  CONSTRAINT fk_cert_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_cert_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  INDEX idx_certificate_number (certificate_number),
  INDEX idx_certificate_verification_code (verification_code)
);

CREATE TABLE IF NOT EXISTS notifications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT,
  title VARCHAR(200) NOT NULL,
  message VARCHAR(1000) NOT NULL,
  type VARCHAR(30) NOT NULL,
  is_read BIT NOT NULL,
  link_url VARCHAR(255),
  CONSTRAINT fk_notifications_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS feedback (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  comment VARCHAR(2000),
  approved BIT NOT NULL,
  CONSTRAINT fk_feedback_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_feedback_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  parent_id BIGINT,
  content VARCHAR(2000) NOT NULL,
  upvotes INT NOT NULL DEFAULT 0,
  moderated BIT NOT NULL,
  CONSTRAINT fk_comments_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_comments_parent FOREIGN KEY (parent_id) REFERENCES comments(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS announcements (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT,
  author_id BIGINT NOT NULL,
  title VARCHAR(180) NOT NULL,
  content VARCHAR(3000) NOT NULL,
  active BIT NOT NULL,
  published_at DATETIME,
  CONSTRAINT fk_announcements_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE SET NULL,
  CONSTRAINT fk_announcements_author FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS volunteers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT NOT NULL UNIQUE,
  skills VARCHAR(500),
  availability VARCHAR(500),
  notes VARCHAR(1000),
  CONSTRAINT fk_volunteers_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS event_volunteers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  volunteer_id BIGINT NOT NULL,
  assignment VARCHAR(120) NOT NULL,
  checked_in BIT NOT NULL,
  CONSTRAINT fk_event_volunteer_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  CONSTRAINT fk_event_volunteer_volunteer FOREIGN KEY (volunteer_id) REFERENCES volunteers(id) ON DELETE CASCADE,
  UNIQUE KEY uq_event_volunteer (event_id, volunteer_id)
);

CREATE TABLE IF NOT EXISTS sponsors (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  event_id BIGINT NOT NULL,
  name VARCHAR(150) NOT NULL,
  logo_url VARCHAR(255),
  website VARCHAR(255),
  amount DECIMAL(12,2),
  CONSTRAINT fk_sponsors_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS audit_logs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT,
  action VARCHAR(120) NOT NULL,
  entity_type VARCHAR(120) NOT NULL,
  entity_id VARCHAR(60),
  details VARCHAR(2000),
  CONSTRAINT fk_audit_logs_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT NOT NULL,
  token VARCHAR(300) NOT NULL UNIQUE,
  expiry_date DATETIME NOT NULL,
  revoked BIT NOT NULL,
  CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS verification_tokens (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT NOT NULL,
  token VARCHAR(200) NOT NULL UNIQUE,
  expiry_date DATETIME NOT NULL,
  used BIT NOT NULL,
  CONSTRAINT fk_verification_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS password_reset_tokens (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id BIGINT NOT NULL,
  token VARCHAR(200) NOT NULL UNIQUE,
  expiry_date DATETIME NOT NULL,
  used BIT NOT NULL,
  CONSTRAINT fk_password_reset_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS file_assets (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  owner_id BIGINT,
  event_id BIGINT,
  file_name VARCHAR(180) NOT NULL,
  content_type VARCHAR(120) NOT NULL,
  storage_path VARCHAR(400) NOT NULL,
  visibility VARCHAR(40) NOT NULL,
  asset_type VARCHAR(50) NOT NULL,
  CONSTRAINT fk_file_assets_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE SET NULL,
  CONSTRAINT fk_file_assets_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE SET NULL
);
