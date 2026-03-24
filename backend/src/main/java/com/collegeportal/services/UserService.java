package com.collegeportal.services;

import com.collegeportal.dto.FileUploadResponse;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.RoleAssignmentRequest;
import com.collegeportal.dto.UserDto;
import com.collegeportal.dto.UserUpdateRequest;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.Role;
import com.collegeportal.entities.RoleName;
import com.collegeportal.entities.User;
import com.collegeportal.exceptions.ResourceNotFoundException;
import com.collegeportal.mappers.UserMapper;
import com.collegeportal.repositories.DepartmentRepository;
import com.collegeportal.repositories.RoleRepository;
import com.collegeportal.repositories.UserRepository;
import com.collegeportal.utils.SecurityUtils;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final FileStorageService fileStorageService;
    private final SecurityUtils securityUtils;

    public User getCurrentUserEntity() {
        return securityUtils.getCurrentUser();
    }

    public UserDto getCurrentUser() {
        return userMapper.toDto(securityUtils.getCurrentUser());
    }

    @Transactional
    public UserDto updateCurrentUser(UserUpdateRequest request) {
        User user = securityUtils.getCurrentUser();
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            user.setDepartment(department);
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto assignRoles(Long userId, RoleAssignmentRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.getRoles().clear();
        request.getRoles().forEach(roleName -> {
            RoleName normalized = roleName.startsWith("ROLE_") ? RoleName.valueOf(roleName) : RoleName.valueOf("ROLE_" + roleName.toUpperCase());
            Role role = roleRepository.findByName(normalized).orElseThrow(() -> new ResourceNotFoundException("Role not found: " + normalized));
            user.getRoles().add(role);
        });
        return userMapper.toDto(userRepository.save(user));
    }

    public PageResponse<UserDto> listUsers(String query, int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<User> specification = (root, q, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query != null && !query.isBlank()) {
                String pattern = "%" + query.toLowerCase() + "%";
                predicates.add(cb.or(cb.like(cb.lower(root.get("firstName")), pattern), cb.like(cb.lower(root.get("lastName")), pattern), cb.like(cb.lower(root.get("email")), pattern)));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
        Page<User> result = userRepository.findAll(specification, pageable);
        return PageResponse.<UserDto>builder().content(result.getContent().stream().map(userMapper::toDto).toList()).page(result.getNumber()).size(result.getSize()).totalElements(result.getTotalElements()).totalPages(result.getTotalPages()).last(result.isLast()).build();
    }

    @Transactional
    public FileUploadResponse uploadAvatar(MultipartFile file) {
        User user = securityUtils.getCurrentUser();
        FileUploadResponse response = fileStorageService.store(file, "AVATAR", user, null, "PUBLIC");
        user.setProfileImageUrl(response.getUrl());
        userRepository.save(user);
        return response;
    }
    public UserService(final UserRepository userRepository, final DepartmentRepository departmentRepository, final RoleRepository roleRepository, final UserMapper userMapper, final FileStorageService fileStorageService, final SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.fileStorageService = fileStorageService;
        this.securityUtils = securityUtils;
    }
}

