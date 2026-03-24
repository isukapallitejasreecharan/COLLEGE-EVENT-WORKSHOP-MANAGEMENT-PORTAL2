package com.collegeportal.dto;

import java.util.List;
import java.util.Map;

public class DashboardStatsDto {
    private long totalUsers;
    private long totalEvents;
    private long totalRegistrations;
    private long totalCertificates;
    private long activeAnnouncements;
    private long unreadNotifications;
    private List<EventDto> upcomingEvents;
    private Map<String, Long> registrationsByDepartment;
    private Map<String, Long> eventsByCategory;
    private Map<String, Long> roleDistribution;
    DashboardStatsDto(final long totalUsers, final long totalEvents, final long totalRegistrations, final long totalCertificates, final long activeAnnouncements, final long unreadNotifications, final List<EventDto> upcomingEvents, final Map<String, Long> registrationsByDepartment, final Map<String, Long> eventsByCategory, final Map<String, Long> roleDistribution) {
        this.totalUsers = totalUsers;
        this.totalEvents = totalEvents;
        this.totalRegistrations = totalRegistrations;
        this.totalCertificates = totalCertificates;
        this.activeAnnouncements = activeAnnouncements;
        this.unreadNotifications = unreadNotifications;
        this.upcomingEvents = upcomingEvents;
        this.registrationsByDepartment = registrationsByDepartment;
        this.eventsByCategory = eventsByCategory;
        this.roleDistribution = roleDistribution;
    }
    public static class DashboardStatsDtoBuilder {
        private long totalUsers;
        private long totalEvents;
        private long totalRegistrations;
        private long totalCertificates;
        private long activeAnnouncements;
        private long unreadNotifications;
        private List<EventDto> upcomingEvents;
        private Map<String, Long> registrationsByDepartment;
        private Map<String, Long> eventsByCategory;
        private Map<String, Long> roleDistribution;
        DashboardStatsDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder totalUsers(final long totalUsers) {
            this.totalUsers = totalUsers;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder totalEvents(final long totalEvents) {
            this.totalEvents = totalEvents;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder totalRegistrations(final long totalRegistrations) {
            this.totalRegistrations = totalRegistrations;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder totalCertificates(final long totalCertificates) {
            this.totalCertificates = totalCertificates;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder activeAnnouncements(final long activeAnnouncements) {
            this.activeAnnouncements = activeAnnouncements;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder unreadNotifications(final long unreadNotifications) {
            this.unreadNotifications = unreadNotifications;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder upcomingEvents(final List<EventDto> upcomingEvents) {
            this.upcomingEvents = upcomingEvents;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder registrationsByDepartment(final Map<String, Long> registrationsByDepartment) {
            this.registrationsByDepartment = registrationsByDepartment;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder eventsByCategory(final Map<String, Long> eventsByCategory) {
            this.eventsByCategory = eventsByCategory;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DashboardStatsDto.DashboardStatsDtoBuilder roleDistribution(final Map<String, Long> roleDistribution) {
            this.roleDistribution = roleDistribution;
            return this;
        }
        public DashboardStatsDto build() {
            return new DashboardStatsDto(this.totalUsers, this.totalEvents, this.totalRegistrations, this.totalCertificates, this.activeAnnouncements, this.unreadNotifications, this.upcomingEvents, this.registrationsByDepartment, this.eventsByCategory, this.roleDistribution);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "DashboardStatsDto.DashboardStatsDtoBuilder(totalUsers=" + this.totalUsers + ", totalEvents=" + this.totalEvents + ", totalRegistrations=" + this.totalRegistrations + ", totalCertificates=" + this.totalCertificates + ", activeAnnouncements=" + this.activeAnnouncements + ", unreadNotifications=" + this.unreadNotifications + ", upcomingEvents=" + this.upcomingEvents + ", registrationsByDepartment=" + this.registrationsByDepartment + ", eventsByCategory=" + this.eventsByCategory + ", roleDistribution=" + this.roleDistribution + ")";
        }
    }
    public static DashboardStatsDto.DashboardStatsDtoBuilder builder() {
        return new DashboardStatsDto.DashboardStatsDtoBuilder();
    }
    public long getTotalUsers() {
        return this.totalUsers;
    }
    public long getTotalEvents() {
        return this.totalEvents;
    }
    public long getTotalRegistrations() {
        return this.totalRegistrations;
    }
    public long getTotalCertificates() {
        return this.totalCertificates;
    }
    public long getActiveAnnouncements() {
        return this.activeAnnouncements;
    }
    public long getUnreadNotifications() {
        return this.unreadNotifications;
    }
    public List<EventDto> getUpcomingEvents() {
        return this.upcomingEvents;
    }
    public Map<String, Long> getRegistrationsByDepartment() {
        return this.registrationsByDepartment;
    }
    public Map<String, Long> getEventsByCategory() {
        return this.eventsByCategory;
    }
    public Map<String, Long> getRoleDistribution() {
        return this.roleDistribution;
    }
}

