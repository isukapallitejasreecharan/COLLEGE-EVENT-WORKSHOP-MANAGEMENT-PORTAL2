package com.collegeportal.dto;

public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String bio;
    private Long departmentId;
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getBio() {
        return this.bio;
    }
    public Long getDepartmentId() {
        return this.departmentId;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    public void setBio(final String bio) {
        this.bio = bio;
    }
    public void setDepartmentId(final Long departmentId) {
        this.departmentId = departmentId;
    }
}

