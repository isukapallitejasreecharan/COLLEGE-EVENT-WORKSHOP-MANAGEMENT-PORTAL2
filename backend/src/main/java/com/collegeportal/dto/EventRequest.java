package com.collegeportal.dto;

import com.collegeportal.entities.EventVisibility;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String posterUrl;
    @NotNull
    private Long categoryId;
    private Long departmentId;
    private Long venueId;
    @NotBlank
    private String eventType;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    @Future
    private LocalDateTime registrationDeadline;
    @NotNull
    private Integer maximumParticipants;
    @NotNull
    private EventVisibility visibility;
    private Boolean multiDay = false;
    private Boolean recurring = false;
    private String recurringRule;
    private String resources;
    private String tags;
    private String faq;
    private String sponsorsText;
    private Boolean registrationRequiresApproval = false;
    private Boolean registrationClosed = false;
    private boolean publishNow;
    private List<Long> speakerIds = new ArrayList<>();
    private List<EventSessionRequest> sessions = new ArrayList<>();
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getPosterUrl() {
        return this.posterUrl;
    }
    public Long getCategoryId() {
        return this.categoryId;
    }
    public Long getDepartmentId() {
        return this.departmentId;
    }
    public Long getVenueId() {
        return this.venueId;
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
    public Boolean getRegistrationRequiresApproval() {
        return this.registrationRequiresApproval;
    }
    public Boolean getRegistrationClosed() {
        return this.registrationClosed;
    }
    public boolean isPublishNow() {
        return this.publishNow;
    }
    public List<Long> getSpeakerIds() {
        return this.speakerIds;
    }
    public List<EventSessionRequest> getSessions() {
        return this.sessions;
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
    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setDepartmentId(final Long departmentId) {
        this.departmentId = departmentId;
    }
    public void setVenueId(final Long venueId) {
        this.venueId = venueId;
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
    public void setRegistrationRequiresApproval(final Boolean registrationRequiresApproval) {
        this.registrationRequiresApproval = registrationRequiresApproval;
    }
    public void setRegistrationClosed(final Boolean registrationClosed) {
        this.registrationClosed = registrationClosed;
    }
    public void setPublishNow(final boolean publishNow) {
        this.publishNow = publishNow;
    }
    public void setSpeakerIds(final List<Long> speakerIds) {
        this.speakerIds = speakerIds;
    }
    public void setSessions(final List<EventSessionRequest> sessions) {
        this.sessions = sessions;
    }
}

