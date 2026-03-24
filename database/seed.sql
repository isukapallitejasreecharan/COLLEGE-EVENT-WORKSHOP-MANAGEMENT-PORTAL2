USE college_event_portal;

INSERT INTO roles (id, name, description) VALUES
(1, 'ROLE_ADMIN', 'Platform administrator'),
(2, 'ROLE_ORGANIZER', 'Event organizer'),
(3, 'ROLE_STUDENT', 'Student participant'),
(4, 'ROLE_FACULTY', 'Faculty participant'),
(5, 'ROLE_VOLUNTEER', 'Volunteer coordinator')
ON DUPLICATE KEY UPDATE description = VALUES(description);

INSERT INTO departments (id, code, name, description) VALUES
(1, 'CSE', 'Computer Science', 'Computer Science and Engineering'),
(2, 'ECE', 'Electronics', 'Electronics and Communication'),
(3, 'MBA', 'Business Administration', 'Management Studies')
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

INSERT INTO categories (id, name, description, color) VALUES
(1, 'Workshop', 'Hands-on workshops', '#2563eb'),
(2, 'Seminar', 'Talks and academic sessions', '#059669'),
(3, 'Hackathon', 'Competitive build events', '#dc2626')
ON DUPLICATE KEY UPDATE description = VALUES(description), color = VALUES(color);

INSERT INTO venues (id, name, location, capacity, indoor, facilities) VALUES
(1, 'Main Auditorium', 'Block A', 500, b'1', 'Projector, Stage, WiFi'),
(2, 'Innovation Lab', 'Block C', 80, b'1', 'Computers, 3D Printer, Smart Board')
ON DUPLICATE KEY UPDATE location = VALUES(location), capacity = VALUES(capacity), facilities = VALUES(facilities);

INSERT INTO users (id, department_id, first_name, last_name, email, password, phone, student_id, employee_id, enabled, email_verified, account_non_locked, failed_login_attempts, last_login_at)
VALUES
(1, 1, 'Krishna', 'Manohar', 'admin@collegeportal.local', '$2a$10$6PjFunzWwTwYbcw92EYhkO3iN2cIYqyn0YBNBBLxSseP1Ne2FMnia', '9990001111', NULL, NULL, b'1', b'1', b'1', 0, NOW()),
(2, 1, 'Devaratha', '', 'organizer@collegeportal.local', '$2a$10$ad.P1Il.Tw045.OpO9ais.y7WQxl9oN315UOXsMusZXIwkkR5Y.3W', '9990002222', NULL, 'EMP1001', b'1', b'1', b'1', 0, NOW()),
(3, 1, 'Nanda', 'Gopal', 'student@collegeportal.local', '$2a$10$VgNUMKaDQRGY2cMxUHSaIuwuz8CuwWa8ug5uHYN70V51iXxuzzMta', '9990003333', 'STU24001', NULL, b'1', b'1', b'1', 0, NOW()),
(4, 2, 'Chatrapathi', 'Sivaji', 'faculty@collegeportal.local', '$2a$10$DvQmlP89n85apaYkE67Mw.yEAHjSDjnnz0P7WcWIqb9EgQtJMm7DO', '9990004444', NULL, 'FAC1202', b'1', b'1', b'1', 0, NOW()),
(5, 3, 'Vijay', 'Surya', 'volunteer@collegeportal.local', '$2a$10$riKt2laYiRSjPb/MIVQqRe5HOfcpg0PfJbDtK9KU2DpG8KfkpRJ8m', '9990005555', 'VOL24002', NULL, b'1', b'1', b'1', 0, NOW())
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name), password = VALUES(password);

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5)
ON DUPLICATE KEY UPDATE role_id = VALUES(role_id);

INSERT INTO volunteers (id, user_id, skills, availability, notes) VALUES
(1, 5, 'Photography, Crowd Management', 'Weekdays', 'Strong communication skills')
ON DUPLICATE KEY UPDATE skills = VALUES(skills), availability = VALUES(availability), notes = VALUES(notes);

INSERT INTO speakers (id, name, designation, organization, email, bio) VALUES
(1, 'Dr. Kavya Iyer', 'AI Research Lead', 'Open Learning Lab', 'kavya@example.com', 'Works on applied AI and education systems.'),
(2, 'Sanjay Patel', 'Product Mentor', 'Campus Ventures', 'sanjay@example.com', 'Mentor for student startups.')
ON DUPLICATE KEY UPDATE designation = VALUES(designation), organization = VALUES(organization), bio = VALUES(bio);

INSERT INTO events (id, category_id, department_id, organizer_id, venue_id, title, description, event_type, start_date, end_date, start_time, end_time, registration_deadline, maximum_participants, visibility, status, multi_day, recurring, resources, tags, faq, sponsors_text, registration_requires_approval, registration_closed, event_code)
VALUES
(1, 1, 1, 2, 2, 'AI for Campus Innovators', 'A practical workshop covering AI tools, prompt engineering, and product thinking for college builders.', 'Workshop', '2026-03-19', '2026-03-19', '10:00:00', '16:00:00', '2026-03-18 11:00:00', 60, 'PUBLIC', 'PUBLISHED', b'0', b'0', 'Slides, worksheets, starter kits', 'ai,prompting,innovation', 'Bring your laptop and college ID.', 'Tech Club', b'0', b'0', 'EVT-AI2026'),
(2, 3, 1, 2, 1, 'Campus Build Sprint', 'A weekend hackathon for solving student experience problems with modern web apps.', 'Hackathon', '2026-03-26', '2026-03-27', '09:00:00', '18:00:00', '2026-03-24 11:00:00', 120, 'PUBLIC', 'PUBLISHED', b'1', b'0', 'Mentor hours, repo template', 'hackathon,startup,fullstack', 'Teams of up to 4 are allowed.', 'Alumni Network', b'1', b'0', 'EVT-BUILD26'),
(3, 2, 2, 2, 1, 'Career Paths in Embedded Systems', 'Faculty-led seminar on careers, certifications, and industry pathways.', 'Seminar', '2026-03-04', '2026-03-04', '11:00:00', '13:00:00', '2026-03-03 11:00:00', 150, 'PUBLIC', 'COMPLETED', b'0', b'0', 'Seminar notes', 'embedded,career', 'Certificates issued after attendance review.', 'ECE Department', b'0', b'1', 'EVT-EMBED26')
ON DUPLICATE KEY UPDATE description = VALUES(description), status = VALUES(status), maximum_participants = VALUES(maximum_participants);

INSERT INTO event_speakers_link (event_id, speaker_id) VALUES
(1, 1), (2, 2)
ON DUPLICATE KEY UPDATE speaker_id = VALUES(speaker_id);

INSERT INTO event_sessions (id, event_id, speaker_id, title, description, session_date, start_time, end_time) VALUES
(1, 1, 1, 'Prompt Engineering Bootcamp', 'Writing reliable prompts', '2026-03-19', '10:00:00', '12:00:00'),
(2, 1, 2, 'Build Your First AI Workflow', 'Hands-on implementation', '2026-03-19', '13:00:00', '16:00:00')
ON DUPLICATE KEY UPDATE description = VALUES(description), start_time = VALUES(start_time), end_time = VALUES(end_time);

INSERT INTO event_registrations (id, event_id, user_id, status, bookmarked, checked_in, registration_date, remarks) VALUES
(1, 1, 3, 'APPROVED', b'1', b'0', '2026-03-13 10:30:00', 'Confirmed seat'),
(2, 2, 4, 'PENDING', b'0', b'0', '2026-03-13 15:00:00', 'Awaiting organizer approval'),
(3, 3, 3, 'APPROVED', b'0', b'1', '2026-03-02 12:00:00', 'Attended')
ON DUPLICATE KEY UPDATE status = VALUES(status), remarks = VALUES(remarks), checked_in = VALUES(checked_in);

INSERT INTO attendance (id, registration_id, event_id, user_id, method, status, check_in_time, late) VALUES
(1, 3, 3, 3, 'MANUAL', 'PRESENT', '2026-03-04 10:55:00', b'0')
ON DUPLICATE KEY UPDATE status = VALUES(status), check_in_time = VALUES(check_in_time);

INSERT INTO certificates (id, event_id, user_id, certificate_number, verification_code, pdf_url, issued_at, emailed) VALUES
(1, 3, 3, 'CERT-EMBED26', 'verify-embed-2026', '/api/files/public/certificate-CERT-EMBED26.pdf', '2026-03-05 09:00:00', b'1')
ON DUPLICATE KEY UPDATE pdf_url = VALUES(pdf_url), issued_at = VALUES(issued_at);

INSERT INTO notifications (id, user_id, title, message, type, is_read, link_url) VALUES
(1, 3, 'Reminder enabled', 'You will receive reminders for AI for Campus Innovators.', 'REMINDER', b'0', '/events/1'),
(2, 2, 'New participant registered', 'Meera Nair joined AI for Campus Innovators.', 'REGISTRATION', b'0', '/registrations')
ON DUPLICATE KEY UPDATE message = VALUES(message), is_read = VALUES(is_read);

INSERT INTO feedback (id, event_id, user_id, rating, comment, approved) VALUES
(1, 3, 3, 5, 'Very informative and well structured.', b'1')
ON DUPLICATE KEY UPDATE rating = VALUES(rating), comment = VALUES(comment), approved = VALUES(approved);

INSERT INTO comments (id, event_id, user_id, parent_id, content, upvotes, moderated) VALUES
(1, 1, 3, NULL, 'Will slides be shared after the session?', 3, b'1')
ON DUPLICATE KEY UPDATE content = VALUES(content), upvotes = VALUES(upvotes), moderated = VALUES(moderated);

INSERT INTO announcements (id, event_id, author_id, title, content, active, published_at) VALUES
(1, 1, 1, 'Workshop registrations open', 'Registrations are now live for AI for Campus Innovators. Limited seats available.', b'1', '2026-03-14 08:00:00')
ON DUPLICATE KEY UPDATE content = VALUES(content), active = VALUES(active);

INSERT INTO event_volunteers (id, event_id, volunteer_id, assignment, checked_in) VALUES
(1, 1, 1, 'Photography desk and participant help', b'0')
ON DUPLICATE KEY UPDATE assignment = VALUES(assignment), checked_in = VALUES(checked_in);

INSERT INTO sponsors (id, event_id, name, logo_url, website, amount) VALUES
(1, 2, 'Alumni Network', NULL, 'https://alumni.example.com', 50000.00)
ON DUPLICATE KEY UPDATE website = VALUES(website), amount = VALUES(amount);

INSERT INTO audit_logs (id, user_id, action, entity_type, entity_id, details) VALUES
(1, 1, 'SEED_IMPORT', 'Database', 'bootstrap', 'Base seed data inserted for portal demo')
ON DUPLICATE KEY UPDATE details = VALUES(details);
