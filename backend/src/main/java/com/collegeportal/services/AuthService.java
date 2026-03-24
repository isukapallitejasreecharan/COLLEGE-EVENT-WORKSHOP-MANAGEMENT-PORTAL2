package com.collegeportal.services;

import com.collegeportal.dto.AuthResponse;
import com.collegeportal.dto.ForgotPasswordRequest;
import com.collegeportal.dto.LoginRequest;
import com.collegeportal.dto.RefreshRequest;
import com.collegeportal.dto.RegisterRequest;
import com.collegeportal.dto.ResetPasswordRequest;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.PasswordResetToken;
import com.collegeportal.entities.RefreshToken;
import com.collegeportal.entities.Role;
import com.collegeportal.entities.RoleName;
import com.collegeportal.entities.User;
import com.collegeportal.entities.VerificationToken;
import com.collegeportal.exceptions.BadRequestException;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.UserMapper;
import com.collegeportal.repositories.DepartmentRepository;
import com.collegeportal.repositories.PasswordResetTokenRepository;
import com.collegeportal.repositories.RefreshTokenRepository;
import com.collegeportal.repositories.RoleRepository;
import com.collegeportal.repositories.UserRepository;
import com.collegeportal.repositories.VerificationTokenRepository;
import com.collegeportal.security.JwtService;
import com.collegeportal.security.PortalUserDetails;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final MailService mailService;
    private final NotificationService notificationService;
    private final AuditService auditService;
    @Value("${app.frontend-url}")
    private String frontendUrl;
    @Value("${app.jwt.refresh-expiration-days}")
    private long refreshExpirationDays;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered");
        }
        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        }
        RoleName roleName = parseRole(request.getRole());
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new ResourceNotFoundException("Role not configured"));
        User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail().toLowerCase()).password(passwordEncoder.encode(request.getPassword())).phone(request.getPhone()).department(department).studentId(request.getStudentId()).employeeId(request.getEmployeeId()).enabled(true).emailVerified(false).accountNonLocked(true).failedLoginAttempts(0).build();
        user.getRoles().add(role);
        user = userRepository.save(user);
        String verificationValue = UUID.randomUUID().toString();
        verificationTokenRepository.save(VerificationToken.builder().user(user).token(verificationValue).expiryDate(LocalDateTime.now().plusDays(2)).used(false).build());
        String verificationLink = frontendUrl + "/verify-email?token=" + verificationValue;
        mailService.sendMail(user.getEmail(), "Verify your account", "Activate your account: " + verificationLink);
        notificationService.create(user, "Welcome to the portal", "Verify your email to unlock full access.", com.collegeportal.entities.NotificationType.SYSTEM, "/verify-email");
        auditService.log(user, "REGISTER", "User", String.valueOf(user.getId()), "New account registration");
        return buildAuthResponse(user, issueRefreshToken(user, false));
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase()).orElseThrow(() -> new BadRequestException("Invalid email or password"));
        unlockIfLockExpired(user);
        if (!Boolean.TRUE.equals(user.getAccountNonLocked())) {
            throw new BadRequestException("Account is temporarily locked. Please try again later.");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            recordFailedLogin(user);
            throw new BadRequestException("Invalid email or password");
        }
        user.setFailedLoginAttempts(0);
        user.setLastLoginAt(LocalDateTime.now());
        if (request.isRememberMe()) {
            user.setRememberMeToken(UUID.randomUUID().toString());
        }
        userRepository.save(user);
        RefreshToken refreshToken = issueRefreshToken(user, request.isRememberMe());
        auditService.log(user, "LOGIN", "User", String.valueOf(user.getId()), "User signed in");
        return buildAuthResponse(user, refreshToken);
    }

    @Transactional
    public AuthResponse refresh(RefreshRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken()).orElseThrow(() -> new BadRequestException("Invalid refresh token"));
        if (Boolean.TRUE.equals(refreshToken.getRevoked()) || refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Refresh token expired or revoked");
        }
        return buildAuthResponse(refreshToken.getUser(), refreshToken);
    }

    @Transactional
    public void verifyEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new BadRequestException("Invalid verification token"));
        if (Boolean.TRUE.equals(verificationToken.getUsed()) || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Verification token expired or already used");
        }
        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        user.setEnabled(true);
        userRepository.save(user);
        verificationToken.setUsed(true);
        verificationTokenRepository.save(verificationToken);
        notificationService.create(user, "Email verified", "Your account has been verified.", com.collegeportal.entities.NotificationType.SYSTEM, "/dashboard");
    }

    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase()).orElseThrow(() -> new BadRequestException("User not found"));
        String token = UUID.randomUUID().toString();
        passwordResetTokenRepository.save(PasswordResetToken.builder().user(user).token(token).expiryDate(LocalDateTime.now().plusHours(3)).used(false).build());
        String resetLink = frontendUrl + "/reset-password?token=" + token;
        mailService.sendMail(user.getEmail(), "Reset your password", "Reset link: " + resetLink);
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        PasswordResetToken token = passwordResetTokenRepository.findByToken(request.getToken()).orElseThrow(() -> new BadRequestException("Invalid reset token"));
        if (Boolean.TRUE.equals(token.getUsed()) || token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Reset token expired or already used");
        }
        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        token.setUsed(true);
        passwordResetTokenRepository.save(token);
        notificationService.create(user, "Password updated", "Your password has been successfully reset.", com.collegeportal.entities.NotificationType.SYSTEM, "/login");
    }

    @Transactional
    public void logout(String refreshTokenValue) {
        refreshTokenRepository.findByToken(refreshTokenValue).ifPresent(token -> {
            token.setRevoked(true);
            refreshTokenRepository.save(token);
        });
    }

    private AuthResponse buildAuthResponse(User user, RefreshToken refreshToken) {
        PortalUserDetails userDetails = new PortalUserDetails(user);
        String accessToken = jwtService.generateToken(userDetails, Map.of("roles", user.getRoles().stream().map(role -> role.getName().name()).toList(), "userId", user.getId()));
        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken.getToken()).tokenType("Bearer").expiresInSeconds(jwtService.getExpirationSeconds()).roles(user.getRoles().stream().map(role -> role.getName().name()).collect(java.util.stream.Collectors.toSet())).user(userMapper.toDto(user)).build();
    }

    private RefreshToken issueRefreshToken(User user, boolean rememberMe) {
        RefreshToken refreshToken = RefreshToken.builder().user(user).token(UUID.randomUUID().toString()).revoked(false).expiryDate(LocalDateTime.now().plusDays(rememberMe ? 30 : refreshExpirationDays)).build();
        return refreshTokenRepository.save(refreshToken);
    }

    private void recordFailedLogin(User user) {
        int attempts = user.getFailedLoginAttempts() == null ? 0 : user.getFailedLoginAttempts();
        attempts++;
        user.setFailedLoginAttempts(attempts);
        if (attempts >= 5) {
            user.setAccountNonLocked(false);
            user.setLockTime(LocalDateTime.now());
        }
        userRepository.save(user);
    }

    private void unlockIfLockExpired(User user) {
        if (!Boolean.TRUE.equals(user.getAccountNonLocked()) && user.getLockTime() != null && user.getLockTime().plusMinutes(30).isBefore(LocalDateTime.now())) {
            user.setAccountNonLocked(true);
            user.setFailedLoginAttempts(0);
            user.setLockTime(null);
            userRepository.save(user);
        }
    }

    private RoleName parseRole(String value) {
        if (value == null || value.isBlank()) {
            return RoleName.ROLE_STUDENT;
        }
        String normalized = value.startsWith("ROLE_") ? value : "ROLE_" + value.toUpperCase();
        return RoleName.valueOf(normalized);
    }
    public AuthService(final UserRepository userRepository, final RoleRepository roleRepository, final DepartmentRepository departmentRepository, final RefreshTokenRepository refreshTokenRepository, final VerificationTokenRepository verificationTokenRepository, final PasswordResetTokenRepository passwordResetTokenRepository, final AuthenticationManager authenticationManager, final PasswordEncoder passwordEncoder, final JwtService jwtService, final UserMapper userMapper, final MailService mailService, final NotificationService notificationService, final AuditService auditService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.mailService = mailService;
        this.notificationService = notificationService;
        this.auditService = auditService;
    }
}

