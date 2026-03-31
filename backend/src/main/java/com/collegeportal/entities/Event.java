package com.collegeportal.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events", indexes = {@Index(name = "idx_event_status_date", columnList = "status,startDate"), @Index(name = "idx_event_department", columnList = "department_id"), @Index(name = "idx_event_category", columnList = "category_id")})
public class Event extends BaseEntity {
    @Column(nullable = false, length = 180)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(length = 255)
    private String posterUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;
    @Column(nullable = false, length = 80)
    private String eventType;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private LocalDateTime registrationDeadline;
    @Column(nullable = false)
    private Integer maximumParticipants;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EventVisibility visibility;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EventStatus status;
    @Column(nullable = false)
    private Boolean multiDay;
    @Column(nullable = false)
    private Boolean recurring;
    @Column(length = 120)
    private String recurringRule;
    @Column(columnDefinition = "TEXT")
    private String resources;
    @Column(columnDefinition = "TEXT")
    private String tags;
    @Column(columnDefinition = "TEXT")
    private String faq;
    @Column(columnDefinition = "TEXT")
    private String sponsorsText;
    @Column(length = 255)
    private String materialsUrl;
    @Column(length = 255)
    private String recordingUrl;
    @Column(columnDefinition = "TEXT")
    private String galleryUrls;
    @Column(nullable = false)
    private Boolean registrationRequiresApproval;
    @Column(nullable = false)
    private Boolean registrationClosed;
    @Column(nullable = false, unique = true, length = 30)
    private String eventCode;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventSession> sessions;
    @ManyToMany
    @JoinTable(name = "event_speakers_link", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private Set<Speaker> speakers;
    private static List<EventSession> $default$sessions() {
        return new ArrayList<>();
    }
    private static Set<Speaker> $default$speakers() {
        return new HashSet<>();
    }
    public static class EventBuilder {
        private String title;
        private String description;
        private String posterUrl;
        private Category category;
        private Department department;
        private User organizer;
        private Venue venue;
        private String eventType;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDateTime registrationDeadline;
        private Integer maximumParticipants;
        private EventVisibility visibility;
        private EventStatus status;
        private Boolean multiDay;
        private Boolean recurring;
        private String recurringRule;
        private String resources;
        private String tags;
        private String faq;
        private String sponsorsText;
        private String materialsUrl;
        private String recordingUrl;
        private String galleryUrls;
        private Boolean registrationRequiresApproval;
        private Boolean registrationClosed;
        private String eventCode;
        private boolean sessions$set;
        private List<EventSession> sessions$value;
        private boolean speakers$set;
        private Set<Speaker> speakers$value;
        EventBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder posterUrl(final String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder category(final Category category) {
            this.category = category;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder department(final Department department) {
            this.department = department;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder organizer(final User organizer) {
            this.organizer = organizer;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder venue(final Venue venue) {
            this.venue = venue;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder startDate(final LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder endDate(final LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder startTime(final LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder endTime(final LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder registrationDeadline(final LocalDateTime registrationDeadline) {
            this.registrationDeadline = registrationDeadline;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder maximumParticipants(final Integer maximumParticipants) {
            this.maximumParticipants = maximumParticipants;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder visibility(final EventVisibility visibility) {
            this.visibility = visibility;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder status(final EventStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder multiDay(final Boolean multiDay) {
            this.multiDay = multiDay;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder recurring(final Boolean recurring) {
            this.recurring = recurring;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder recurringRule(final String recurringRule) {
            this.recurringRule = recurringRule;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder resources(final String resources) {
            this.resources = resources;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder tags(final String tags) {
            this.tags = tags;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder faq(final String faq) {
            this.faq = faq;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder sponsorsText(final String sponsorsText) {
            this.sponsorsText = sponsorsText;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder materialsUrl(final String materialsUrl) {
            this.materialsUrl = materialsUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder recordingUrl(final String recordingUrl) {
            this.recordingUrl = recordingUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder galleryUrls(final String galleryUrls) {
            this.galleryUrls = galleryUrls;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder registrationRequiresApproval(final Boolean registrationRequiresApproval) {
            this.registrationRequiresApproval = registrationRequiresApproval;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder registrationClosed(final Boolean registrationClosed) {
            this.registrationClosed = registrationClosed;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder eventCode(final String eventCode) {
            this.eventCode = eventCode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder sessions(final List<EventSession> sessions) {
            this.sessions$value = sessions;
            sessions$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Event.EventBuilder speakers(final Set<Speaker> speakers) {
            this.speakers$value = speakers;
            speakers$set = true;
            return this;
        }
        public Event build() {
            List<EventSession> sessions$value = this.sessions$value;
            if (!this.sessions$set) sessions$value = Event.$default$sessions();
            Set<Speaker> speakers$value = this.speakers$value;
            if (!this.speakers$set) speakers$value = Event.$default$speakers();
            return new Event(this.title, this.description, this.posterUrl, this.category, this.department, this.organizer, this.venue, this.eventType, this.startDate, this.endDate, this.startTime, this.endTime, this.registrationDeadline, this.maximumParticipants, this.visibility, this.status, this.multiDay, this.recurring, this.recurringRule, this.resources, this.tags, this.faq, this.sponsorsText, this.materialsUrl, this.recordingUrl, this.galleryUrls, this.registrationRequiresApproval, this.registrationClosed, this.eventCode, sessions$value, speakers$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Event.EventBuilder(title=" + this.title + ", description=" + this.description + ", posterUrl=" + this.posterUrl + ", category=" + this.category + ", department=" + this.department + ", organizer=" + this.organizer + ", venue=" + this.venue + ", eventType=" + this.eventType + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", registrationDeadline=" + this.registrationDeadline + ", maximumParticipants=" + this.maximumParticipants + ", visibility=" + this.visibility + ", status=" + this.status + ", multiDay=" + this.multiDay + ", recurring=" + this.recurring + ", recurringRule=" + this.recurringRule + ", resources=" + this.resources + ", tags=" + this.tags + ", faq=" + this.faq + ", sponsorsText=" + this.sponsorsText + ", materialsUrl=" + this.materialsUrl + ", recordingUrl=" + this.recordingUrl + ", galleryUrls=" + this.galleryUrls + ", registrationRequiresApproval=" + this.registrationRequiresApproval + ", registrationClosed=" + this.registrationClosed + ", eventCode=" + this.eventCode + ", sessions$value=" + this.sessions$value + ", speakers$value=" + this.speakers$value + ")";
        }
    }
    public static Event.EventBuilder builder() {
        return new Event.EventBuilder();
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getPosterUrl() {
        return this.posterUrl;
    }
    public Category getCategory() {
        return this.category;
    }
    public Department getDepartment() {
        return this.department;
    }
    public User getOrganizer() {
        return this.organizer;
    }
    public Venue getVenue() {
        return this.venue;
    }
    public String getEventType() {
        return this.eventType;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return this.endTime;
    }
    public LocalDateTime getRegistrationDeadline() {
        return this.registrationDeadline;
    }
    public Integer getMaximumParticipants() {
        return this.maximumParticipants;
    }
    public EventVisibility getVisibility() {
        return this.visibility;
    }
    public EventStatus getStatus() {
        return this.status;
    }
    public Boolean getMultiDay() {
        return this.multiDay;
    }
    public Boolean getRecurring() {
        return this.recurring;
    }
    public String getRecurringRule() {
        return this.recurringRule;
    }
    public String getResources() {
        return this.resources;
    }
    public String getTags() {
        return this.tags;
    }
    public String getFaq() {
        return this.faq;
    }
    public String getSponsorsText() {
        return this.sponsorsText;
    }
    public String getMaterialsUrl() {
        return this.materialsUrl;
    }
    public String getRecordingUrl() {
        return this.recordingUrl;
    }
    public String getGalleryUrls() {
        return this.galleryUrls;
    }
    public Boolean getRegistrationRequiresApproval() {
        return this.registrationRequiresApproval;
    }
    public Boolean getRegistrationClosed() {
        return this.registrationClosed;
    }
    public String getEventCode() {
        return this.eventCode;
    }
    public List<EventSession> getSessions() {
        return this.sessions;
    }
    public Set<Speaker> getSpeakers() {
        return this.speakers;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setPosterUrl(final String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public void setCategory(final Category category) {
        this.category = category;
    }
    public void setDepartment(final Department department) {
        this.department = department;
    }
    public void setOrganizer(final User organizer) {
        this.organizer = organizer;
    }
    public void setVenue(final Venue venue) {
        this.venue = venue;
    }
    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setRegistrationDeadline(final LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }
    public void setMaximumParticipants(final Integer maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }
    public void setVisibility(final EventVisibility visibility) {
        this.visibility = visibility;
    }
    public void setStatus(final EventStatus status) {
        this.status = status;
    }
    public void setMultiDay(final Boolean multiDay) {
        this.multiDay = multiDay;
    }
    public void setRecurring(final Boolean recurring) {
        this.recurring = recurring;
    }
    public void setRecurringRule(final String recurringRule) {
        this.recurringRule = recurringRule;
    }
    public void setResources(final String resources) {
        this.resources = resources;
    }
    public void setTags(final String tags) {
        this.tags = tags;
    }
    public void setFaq(final String faq) {
        this.faq = faq;
    }
    public void setSponsorsText(final String sponsorsText) {
        this.sponsorsText = sponsorsText;
    }
    public void setMaterialsUrl(final String materialsUrl) {
        this.materialsUrl = materialsUrl;
    }
    public void setRecordingUrl(final String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }
    public void setGalleryUrls(final String galleryUrls) {
        this.galleryUrls = galleryUrls;
    }
    public void setRegistrationRequiresApproval(final Boolean registrationRequiresApproval) {
        this.registrationRequiresApproval = registrationRequiresApproval;
    }
    public void setRegistrationClosed(final Boolean registrationClosed) {
        this.registrationClosed = registrationClosed;
    }
    public void setEventCode(final String eventCode) {
        this.eventCode = eventCode;
    }
    public void setSessions(final List<EventSession> sessions) {
        this.sessions = sessions;
    }
    public void setSpeakers(final Set<Speaker> speakers) {
        this.speakers = speakers;
    }
    public Event() {
        this.sessions = Event.$default$sessions();
        this.speakers = Event.$default$speakers();
    }
    public Event(final String title, final String description, final String posterUrl, final Category category, final Department department, final User organizer, final Venue venue, final String eventType, final LocalDate startDate, final LocalDate endDate, final LocalTime startTime, final LocalTime endTime, final LocalDateTime registrationDeadline, final Integer maximumParticipants, final EventVisibility visibility, final EventStatus status, final Boolean multiDay, final Boolean recurring, final String recurringRule, final String resources, final String tags, final String faq, final String sponsorsText, final String materialsUrl, final String recordingUrl, final String galleryUrls, final Boolean registrationRequiresApproval, final Boolean registrationClosed, final String eventCode, final List<EventSession> sessions, final Set<Speaker> speakers) {
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.category = category;
        this.department = department;
        this.organizer = organizer;
        this.venue = venue;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationDeadline = registrationDeadline;
        this.maximumParticipants = maximumParticipants;
        this.visibility = visibility;
        this.status = status;
        this.multiDay = multiDay;
        this.recurring = recurring;
        this.recurringRule = recurringRule;
        this.resources = resources;
        this.tags = tags;
        this.faq = faq;
        this.sponsorsText = sponsorsText;
        this.materialsUrl = materialsUrl;
        this.recordingUrl = recordingUrl;
        this.galleryUrls = galleryUrls;
        this.registrationRequiresApproval = registrationRequiresApproval;
        this.registrationClosed = registrationClosed;
        this.eventCode = eventCode;
        this.sessions = sessions;
        this.speakers = speakers;
    }
}

