package com.collegeportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    private String phone;
    private Long departmentId;
    private String role;
    private String studentId;
    private String employeeId;
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getPhone() {
        return this.phone;
    }
    public Long getDepartmentId() {
        return this.departmentId;
    }
    public String getRole() {
        return this.role;
    }
    public String getStudentId() {
        return this.studentId;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    public void setDepartmentId(final Long departmentId) {
        this.departmentId = departmentId;
    }
    public void setRole(final String role) {
        this.role = role;
    }
    public void setStudentId(final String studentId) {
        this.studentId = studentId;
    }
    public void setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
    }
}

