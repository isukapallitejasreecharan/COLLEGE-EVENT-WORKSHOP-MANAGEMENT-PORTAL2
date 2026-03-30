package com.collegeportal.service.impl;

import com.collegeportal.dto.AuthDtos;
import com.collegeportal.entity.RefreshToken;
import com.collegeportal.entity.RoleName;
import com.collegeportal.entity.User;
import com.collegeportal.exception.BadRequestException;
import com.collegeportal.repository.RefreshTokenRepository;
import com.collegeportal.repository.RoleRepository;
import com.collegeportal.repository.UserRepository;
import com.collegeportal.security.CustomUserDetailsService;
import com.collegeportal.security.JwtService;
import com.collegeportal.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService,
            JwtService jwtService,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Value("${app.jwt.refresh-token-expiration-ms}")
    private long refreshExpiration;

    @Override
    public AuthDtos.TokenResponse register(AuthDtos.RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Email already registered");
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEnabled(true);
        user.getRoles().add(roleRepository.findByName(RoleName.STUDENT)
                .orElseThrow(() -> new BadRequestException("Default role missing")));
        userRepository.save(user);

        return issueTokens(user.getEmail());
    }

    @Override
    public AuthDtos.TokenResponse login(AuthDtos.LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        return issueTokens(request.email());
    }

    @Override
    public AuthDtos.TokenResponse refresh(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .filter(t -> t.getExpiryDate().isAfter(Instant.now()))
                .orElseThrow(() -> new BadRequestException("Invalid refresh token"));

        return issueTokens(token.getUser().getEmail());
    }

    private AuthDtos.TokenResponse issueTokens(String email) {
        var userDetails = customUserDetailsService.loadUserByUsername(email);
        String access = jwtService.generateToken(userDetails);

        refreshTokenRepository.deleteByUserId(userRepository.findByEmail(email).orElseThrow().getId());
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(userRepository.findByEmail(email).orElseThrow());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpiration));
        refreshTokenRepository.save(refreshToken);

        return new AuthDtos.TokenResponse(access, refreshToken.getToken(), "Bearer");
    }
}
