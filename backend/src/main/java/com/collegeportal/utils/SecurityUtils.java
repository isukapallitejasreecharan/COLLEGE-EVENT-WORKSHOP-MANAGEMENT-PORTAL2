package com.collegeportal.utils;

import com.collegeportal.entities.User;
import com.collegeportal.exceptions.UnauthorizedException;
import com.collegeportal.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new UnauthorizedException("Authentication required");
        }
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UnauthorizedException("Authenticated user not found"));
    }
    public SecurityUtils(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

