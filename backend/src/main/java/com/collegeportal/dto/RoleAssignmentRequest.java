package com.collegeportal.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public class RoleAssignmentRequest {
    @NotEmpty
    private Set<String> roles;
    public Set<String> getRoles() {
        return this.roles;
    }
    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }
}

