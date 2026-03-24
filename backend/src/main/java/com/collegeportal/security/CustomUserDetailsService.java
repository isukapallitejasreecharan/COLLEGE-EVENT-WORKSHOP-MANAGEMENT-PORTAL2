package com.collegeportal.security;

import com.collegeportal.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(PortalUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

