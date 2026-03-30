package com.collegeportal.config;

import com.collegeportal.entities.Announcement;
import com.collegeportal.entities.Attendance;
import com.collegeportal.entities.AttendanceMethod;
import com.collegeportal.entities.AttendanceStatus;
import com.collegeportal.entities.Category;
import com.collegeportal.entities.Comment;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.Event;
import com.collegeportal.entities.EventRegistration;
import com.collegeportal.entities.EventSession;
import com.collegeportal.entities.EventStatus;
import com.collegeportal.entities.EventVisibility;
import com.collegeportal.entities.Feedback;
import com.collegeportal.entities.Notification;
import com.collegeportal.entities.NotificationType;
import com.collegeportal.entities.RegistrationStatus;
import com.collegeportal.entities.Role;
import com.collegeportal.entities.RoleName;
import com.collegeportal.entities.Speaker;
import com.collegeportal.entities.User;
import com.collegeportal.entities.Venue;
import com.collegeportal.entities.Volunteer;
import com.collegeportal.repositories.AnnouncementRepository;
import com.collegeportal.repositories.AttendanceRepository;
import com.collegeportal.repositories.CategoryRepository;
import com.collegeportal.repositories.CommentRepository;
import com.collegeportal.repositories.DepartmentRepository;
import com.collegeportal.repositories.EventRegistrationRepository;
import com.collegeportal.repositories.EventRepository;
import com.collegeportal.repositories.FeedbackRepository;
import com.collegeportal.repositories.NotificationRepository;
import com.collegeportal.repositories.RoleRepository;
import com.collegeportal.repositories.SpeakerRepository;
import com.collegeportal.repositories.UserRepository;
import com.collegeportal.repositories.VenueRepository;
import com.collegeportal.repositories.VolunteerRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final CategoryRepository categoryRepository;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final SpeakerRepository speakerRepository;
    private final EventRepository eventRepository;
    private final EventRegistrationRepository registrationRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeedbackRepository feedbackRepository;
    private final CommentRepository commentRepository;
    private final AnnouncementRepository announcementRepository;
    private final VolunteerRepository volunteerRepository;
    private final NotificationRepository notificationRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner seedData() {
        return args -> {
            if (roleRepository.count() > 0) {
                return;
            }

            Role adminRole = roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN).description("Platform administrator").build());
            Role organizerRole = roleRepository.save(Role.builder().name(RoleName.ROLE_ORGANIZER).description("Event organizer").build());
            Role studentRole = roleRepository.save(Role.builder().name(RoleName.ROLE_STUDENT).description("Student participant").build());
            Role facultyRole = roleRepository.save(Role.builder().name(RoleName.ROLE_FACULTY).description("Faculty participant").build());
            Role volunteerRole = roleRepository.save(Role.builder().name(RoleName.ROLE_VOLUNTEER).description("Volunteer").build());

            Department cse = departmentRepository.save(Department.builder().code("CSE").name("Computer Science").description("Computer Science and Engineering").build());
            Department ece = departmentRepository.save(Department.builder().code("ECE").name("Electronics").description("Electronics and Communication").build());
            Department mba = departmentRepository.save(Department.builder().code("MBA").name("Business Administration").description("Management Studies").build());

            Category workshop = categoryRepository.save(Category.builder().name("Workshop").description("Hands-on workshops").color("#2563eb").build());
            Category seminar = categoryRepository.save(Category.builder().name("Seminar").description("Talks and sessions").color("#059669").build());
            Category hackathon = categoryRepository.save(Category.builder().name("Hackathon").description("Competitive building events").color("#dc2626").build());

            Venue auditorium = venueRepository.save(Venue.builder().name("Main Auditorium").location("Block A").capacity(500).indoor(true).facilities("Projector, Stage, WiFi").build());
            Venue lab = venueRepository.save(Venue.builder().name("Innovation Lab").location("Block C").capacity(80).indoor(true).facilities("Computers, 3D Printer, Smart Board").build());

            User admin = User.builder().firstName("Krishna").lastName("Manohar").email("admin@collegeportal.local").password(passwordEncoder.encode("Admin@123")).phone("9990001111").department(cse).enabled(true).emailVerified(true).accountNonLocked(true).failedLoginAttempts(0).build();
            admin.getRoles().add(adminRole);
            admin = userRepository.save(admin);

            User organizer = User.builder().firstName("Devaratha").lastName("").email("organizer@collegeportal.local").password(passwordEncoder.encode("Organizer@123")).phone("9990002222").department(cse).enabled(true).emailVerified(true).accountNonLocked(true).failedLoginAttempts(0).employeeId("EMP1001").build();
            organizer.getRoles().add(organizerRole);
            organizer = userRepository.save(organizer);

            User student = User.builder().firstName("Nanda").lastName("Gopal").email("student@collegeportal.local").password(passwordEncoder.encode("Student@123")).phone("9990003333").department(cse).enabled(true).emailVerified(true).accountNonLocked(true).failedLoginAttempts(0).studentId("STU24001").build();
            student.getRoles().add(studentRole);
            student = userRepository.save(student);

            User faculty = User.builder().firstName("Chatrapathi").lastName("Sivaji").email("faculty@collegeportal.local").password(passwordEncoder.encode("Faculty@123")).phone("9990004444").department(ece).enabled(true).emailVerified(true).accountNonLocked(true).failedLoginAttempts(0).employeeId("FAC1202").build();
            faculty.getRoles().add(facultyRole);
            faculty = userRepository.save(faculty);

            User volunteerUser = User.builder().firstName("Vijay").lastName("Surya").email("volunteer@collegeportal.local").password(passwordEncoder.encode("Volunteer@123")).phone("9990005555").department(mba).enabled(true).emailVerified(true).accountNonLocked(true).failedLoginAttempts(0).studentId("VOL24002").build();
            volunteerUser.getRoles().add(volunteerRole);
            volunteerUser = userRepository.save(volunteerUser);

            volunteerRepository.save(Volunteer.builder().user(volunteerUser).skills("Photography, Crowd Management").availability("Weekdays").notes("Strong communication skills").build());

            Speaker speaker1 = speakerRepository.save(Speaker.builder().name("Dr. Kavya Iyer").designation("AI Research Lead").organization("Open Learning Lab").email("kavya@example.com").bio("Works on applied AI and education systems.").build());
            Speaker speaker2 = speakerRepository.save(Speaker.builder().name("Sanjay Patel").designation("Product Mentor").organization("Campus Ventures").email("sanjay@example.com").bio("Mentor for student startups.").build());

            Event upcomingWorkshop = Event.builder().title("AI for Campus Innovators").description("A practical workshop covering AI tools, prompt engineering, and product thinking for college builders.").category(workshop).department(cse).organizer(organizer).venue(lab).eventType("Workshop").startDate(LocalDate.now().plusDays(5)).endDate(LocalDate.now().plusDays(5)).startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(16, 0)).registrationDeadline(LocalDateTime.now().plusDays(4)).maximumParticipants(60).visibility(EventVisibility.PUBLIC).status(EventStatus.PUBLISHED).multiDay(false).recurring(false).resources("Slides, worksheets, starter kits").tags("ai,prompting,innovation").faq("Bring your laptop and college ID.").sponsorsText("Tech Club").registrationRequiresApproval(false).registrationClosed(false).eventCode("EVT-AI2026").build();
            upcomingWorkshop.getSpeakers().add(speaker1);
            upcomingWorkshop.getSessions().addAll(List.of(
                    EventSession.builder().event(upcomingWorkshop).title("Prompt Engineering Bootcamp").description("Writing reliable prompts").sessionDate(LocalDate.now().plusDays(5)).startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(12, 0)).speaker(speaker1).build(),
                    EventSession.builder().event(upcomingWorkshop).title("Build Your First AI Workflow").description("Hands-on implementation").sessionDate(LocalDate.now().plusDays(5)).startTime(LocalTime.of(13, 0)).endTime(LocalTime.of(16, 0)).speaker(speaker2).build()));
            upcomingWorkshop = eventRepository.save(upcomingWorkshop);

            Event upcomingHackathon = Event.builder().title("Campus Build Sprint").description("A weekend hackathon for solving student experience problems with modern web apps.").category(hackathon).department(cse).organizer(organizer).venue(auditorium).eventType("Hackathon").startDate(LocalDate.now().plusDays(12)).endDate(LocalDate.now().plusDays(13)).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(18, 0)).registrationDeadline(LocalDateTime.now().plusDays(10)).maximumParticipants(120).visibility(EventVisibility.PUBLIC).status(EventStatus.PUBLISHED).multiDay(true).recurring(false).resources("Mentor hours, repo template").tags("hackathon,startup,fullstack").faq("Teams of up to 4 are allowed.").sponsorsText("Alumni Network").registrationRequiresApproval(true).registrationClosed(false).eventCode("EVT-BUILD26").build();
            upcomingHackathon.getSpeakers().add(speaker2);
            upcomingHackathon = eventRepository.save(upcomingHackathon);

            Event completedSeminar = Event.builder().title("Career Paths in Embedded Systems").description("Faculty-led seminar on careers, certifications, and industry pathways.").category(seminar).department(ece).organizer(organizer).venue(auditorium).eventType("Seminar").startDate(LocalDate.now().minusDays(10)).endDate(LocalDate.now().minusDays(10)).startTime(LocalTime.of(11, 0)).endTime(LocalTime.of(13, 0)).registrationDeadline(LocalDateTime.now().minusDays(11)).maximumParticipants(150).visibility(EventVisibility.PUBLIC).status(EventStatus.COMPLETED).multiDay(false).recurring(false).resources("Seminar notes").tags("embedded,career").faq("Certificates issued after attendance review.").sponsorsText("ECE Department").registrationRequiresApproval(false).registrationClosed(true).eventCode("EVT-EMBED26").build();
            completedSeminar = eventRepository.save(completedSeminar);

            registrationRepository.save(EventRegistration.builder().event(upcomingWorkshop).user(student).status(RegistrationStatus.APPROVED).bookmarked(true).checkedIn(false).registrationDate(LocalDateTime.now().minusDays(1)).remarks("Confirmed seat").build());
            registrationRepository.save(EventRegistration.builder().event(upcomingHackathon).user(faculty).status(RegistrationStatus.PENDING).bookmarked(false).checkedIn(false).registrationDate(LocalDateTime.now().minusHours(10)).remarks("Awaiting organizer approval").build());
            EventRegistration completedReg = registrationRepository.save(EventRegistration.builder().event(completedSeminar).user(student).status(RegistrationStatus.APPROVED).bookmarked(false).checkedIn(true).registrationDate(LocalDateTime.now().minusDays(12)).remarks("Attended").build());

            attendanceRepository.save(Attendance.builder().registration(completedReg).event(completedSeminar).attendee(student).method(AttendanceMethod.MANUAL).status(AttendanceStatus.PRESENT).checkInTime(LocalDateTime.now().minusDays(10).withHour(10).withMinute(55)).late(false).build());
            feedbackRepository.save(Feedback.builder().event(completedSeminar).user(student).rating(5).comment("Very informative and well structured.").approved(true).build());
            commentRepository.save(Comment.builder().event(upcomingWorkshop).user(student).content("Will slides be shared after the session?").upvotes(3).moderated(true).build());
            announcementRepository.save(Announcement.builder().author(admin).event(upcomingWorkshop).title("Workshop registrations open").content("Registrations are now live for AI for Campus Innovators. Limited seats available.").active(true).publishedAt(LocalDateTime.now().minusHours(6)).build());
            notificationRepository.save(Notification.builder().user(student).title("Reminder enabled").message("You will receive reminders for AI for Campus Innovators.").type(NotificationType.REMINDER).isRead(false).linkUrl("/events/1").build());
            notificationRepository.save(Notification.builder().user(organizer).title("New participant registered").message(student.getFullName() + " joined AI for Campus Innovators.").type(NotificationType.REGISTRATION).isRead(false).linkUrl("/registrations").build());
        };
    }

    public DataInitializer(final RoleRepository roleRepository, final DepartmentRepository departmentRepository, final CategoryRepository categoryRepository, final VenueRepository venueRepository, final UserRepository userRepository, final SpeakerRepository speakerRepository, final EventRepository eventRepository, final EventRegistrationRepository registrationRepository, final AttendanceRepository attendanceRepository, final FeedbackRepository feedbackRepository, final CommentRepository commentRepository, final AnnouncementRepository announcementRepository, final VolunteerRepository volunteerRepository, final NotificationRepository notificationRepository, final PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.categoryRepository = categoryRepository;
        this.venueRepository = venueRepository;
        this.userRepository = userRepository;
        this.speakerRepository = speakerRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.attendanceRepository = attendanceRepository;
        this.feedbackRepository = feedbackRepository;
        this.commentRepository = commentRepository;
        this.announcementRepository = announcementRepository;
        this.volunteerRepository = volunteerRepository;
        this.notificationRepository = notificationRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
