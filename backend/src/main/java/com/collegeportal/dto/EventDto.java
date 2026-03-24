package com.collegeportal.dto;

import com.collegeportal.entities.EventStatus;
import com.collegeportal.entities.EventVisibility;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EventDto {
    private Long id;
    private String title;
    private String description;
    private String posterUrl;
    private String categoryName;
    private String departmentName;
    private String organizerName;
    private String venueName;
    private String venueLocation;
    private String eventType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime registrationDeadline;
    private Integer maximumParticipants;
    private Long registeredParticipants;
    private Long availableSeats;
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
    private String eventCode;
    private Boolean registrationRequiresApproval;
    private Boolean registrationClosed;
    private List<EventSessionDto> sessions;
    private List<String> speakers;
    EventDto(final Long id, final String title, final String description, final String posterUrl, final String categoryName, final String departmentName, final String organizerName, final String venueName, final String venueLocation, final String eventType, final LocalDate startDate, final LocalDate endDate, final LocalTime startTime, final LocalTime endTime, final LocalDateTime registrationDeadline, final Integer maximumParticipants, final Long registeredParticipants, final Long availableSeats, final EventVisibility visibility, final EventStatus status, final Boolean multiDay, final Boolean recurring, final String recurringRule, final String resources, final String tags, final String faq, final String sponsorsText, final String materialsUrl, final String recordingUrl, final String eventCode, final Boolean registrationRequiresApproval, final Boolean registrationClosed, final List<EventSessionDto> sessions, final List<String> speakers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.categoryName = categoryName;
        this.departmentName = departmentName;
        this.organizerName = organizerName;
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registrationDeadline = registrationDeadline;
        this.maximumParticipants = maximumParticipants;
        this.registeredParticipants = registeredParticipants;
        this.availableSeats = availableSeats;
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
        this.eventCode = eventCode;
        this.registrationRequiresApproval = registrationRequiresApproval;
        this.registrationClosed = registrationClosed;
        this.sessions = sessions;
        this.speakers = speakers;
    }
    public static class EventDtoBuilder {
        private Long id;
        private String title;
        private String description;
        private String posterUrl;
        private String categoryName;
        private String departmentName;
        private String organizerName;
        private String venueName;
        private String venueLocation;
        private String eventType;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalDateTime registrationDeadline;
        private Integer maximumParticipants;
        private Long registeredParticipants;
        private Long availableSeats;
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
        private String eventCode;
        private Boolean registrationRequiresApproval;
        private Boolean registrationClosed;
        private List<EventSessionDto> sessions;
        private List<String> speakers;
        EventDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder posterUrl(final String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder categoryName(final String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder departmentName(final String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder organizerName(final String organizerName) {
            this.organizerName = organizerName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder venueName(final String venueName) {
            this.venueName = venueName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder venueLocation(final String venueLocation) {
            this.venueLocation = venueLocation;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder startDate(final LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder endDate(final LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder startTime(final LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder endTime(final LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder registrationDeadline(final LocalDateTime registrationDeadline) {
            this.registrationDeadline = registrationDeadline;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder maximumParticipants(final Integer maximumParticipants) {
            this.maximumParticipants = maximumParticipants;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder registeredParticipants(final Long registeredParticipants) {
            this.registeredParticipants = registeredParticipants;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder availableSeats(final Long availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder visibility(final EventVisibility visibility) {
            this.visibility = visibility;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder status(final EventStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder multiDay(final Boolean multiDay) {
            this.multiDay = multiDay;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder recurring(final Boolean recurring) {
            this.recurring = recurring;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder recurringRule(final String recurringRule) {
            this.recurringRule = recurringRule;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder resources(final String resources) {
            this.resources = resources;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder tags(final String tags) {
            this.tags = tags;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder faq(final String faq) {
            this.faq = faq;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder sponsorsText(final String sponsorsText) {
            this.sponsorsText = sponsorsText;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder materialsUrl(final String materialsUrl) {
            this.materialsUrl = materialsUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder recordingUrl(final String recordingUrl) {
            this.recordingUrl = recordingUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder eventCode(final String eventCode) {
            this.eventCode = eventCode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder registrationRequiresApproval(final Boolean registrationRequiresApproval) {
            this.registrationRequiresApproval = registrationRequiresApproval;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder registrationClosed(final Boolean registrationClosed) {
            this.registrationClosed = registrationClosed;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder sessions(final List<EventSessionDto> sessions) {
            this.sessions = sessions;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public EventDto.EventDtoBuilder speakers(final List<String> speakers) {
            this.speakers = speakers;
            return this;
        }
        public EventDto build() {
            return new EventDto(this.id, this.title, this.description, this.posterUrl, this.categoryName, this.departmentName, this.organizerName, this.venueName, this.venueLocation, this.eventType, this.startDate, this.endDate, this.startTime, this.endTime, this.registrationDeadline, this.maximumParticipants, this.registeredParticipants, this.availableSeats, this.visibility, this.status, this.multiDay, this.recurring, this.recurringRule, this.resources, this.tags, this.faq, this.sponsorsText, this.materialsUrl, this.recordingUrl, this.eventCode, this.registrationRequiresApproval, this.registrationClosed, this.sessions, this.speakers);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "EventDto.EventDtoBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", posterUrl=" + this.posterUrl + ", categoryName=" + this.categoryName + ", departmentName=" + this.departmentName + ", organizerName=" + this.organizerName + ", venueName=" + this.venueName + ", venueLocation=" + this.venueLocation + ", eventType=" + this.eventType + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", registrationDeadline=" + this.registrationDeadline + ", maximumParticipants=" + this.maximumParticipants + ", registeredParticipants=" + this.registeredParticipants + ", availableSeats=" + this.availableSeats + ", visibility=" + this.visibility + ", status=" + this.status + ", multiDay=" + this.multiDay + ", recurring=" + this.recurring + ", recurringRule=" + this.recurringRule + ", resources=" + this.resources + ", tags=" + this.tags + ", faq=" + this.faq + ", sponsorsText=" + this.sponsorsText + ", materialsUrl=" + this.materialsUrl + ", recordingUrl=" + this.recordingUrl + ", eventCode=" + this.eventCode + ", registrationRequiresApproval=" + this.registrationRequiresApproval + ", registrationClosed=" + this.registrationClosed + ", sessions=" + this.sessions + ", speakers=" + this.speakers + ")";
        }
    }
    public static EventDto.EventDtoBuilder builder() {
        return new EventDto.EventDtoBuilder();
    }
    public Long getId() {
        return this.id;
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
    public String getCategoryName() {
        return this.categoryName;
    }
    public String getDepartmentName() {
        return this.departmentName;
    }
    public String getOrganizerName() {
        return this.organizerName;
    }
    public String getVenueName() {
        return this.venueName;
    }
    public String getVenueLocation() {
        return this.venueLocation;
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
    public Long getRegisteredParticipants() {
        return this.registeredParticipants;
    }
    public Long getAvailableSeats() {
        return this.availableSeats;
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
    public String getEventCode() {
        return this.eventCode;
    }
    public Boolean getRegistrationRequiresApproval() {
        return this.registrationRequiresApproval;
    }
    public Boolean getRegistrationClosed() {
        return this.registrationClosed;
    }
    public List<EventSessionDto> getSessions() {
        return this.sessions;
    }
    public List<String> getSpeakers() {
        return this.speakers;
    }
}

