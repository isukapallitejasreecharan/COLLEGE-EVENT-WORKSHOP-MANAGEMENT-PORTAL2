package com.collegeportal.controllers;

import com.collegeportal.dto.ApiResponse;
import com.collegeportal.dto.AuditLogDto;
import com.collegeportal.dto.CategoryDto;
import com.collegeportal.dto.DashboardStatsDto;
import com.collegeportal.dto.DepartmentDto;
import com.collegeportal.dto.PageResponse;
import com.collegeportal.dto.VenueDto;
import com.collegeportal.entities.Category;
import com.collegeportal.entities.Department;
import com.collegeportal.entities.Venue;
import com.collegeportal.services.AdminService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole(\'ADMIN\')")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/dashboard")
    public ApiResponse<DashboardStatsDto> dashboard() {
        return ApiResponse.<DashboardStatsDto>builder().success(true).message("Dashboard fetched").data(adminService.dashboard(null)).build();
    }

    @GetMapping("/audit-logs")
    public ApiResponse<PageResponse<AuditLogDto>> auditLogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<AuditLogDto>>builder().success(true).message("Audit logs fetched").data(adminService.auditLogs(page, size)).build();
    }

    @GetMapping("/categories")
    public ApiResponse<List<CategoryDto>> categories() {
        return ApiResponse.<List<CategoryDto>>builder().success(true).message("Categories fetched").data(adminService.categories()).build();
    }

    @GetMapping("/departments")
    public ApiResponse<List<DepartmentDto>> departments() {
        return ApiResponse.<List<DepartmentDto>>builder().success(true).message("Departments fetched").data(adminService.departments()).build();
    }

    @GetMapping("/venues")
    public ApiResponse<List<VenueDto>> venues() {
        return ApiResponse.<List<VenueDto>>builder().success(true).message("Venues fetched").data(adminService.venues()).build();
    }

    @PostMapping("/categories")
    public ApiResponse<CategoryDto> saveCategory(@RequestBody Category category) {
        return ApiResponse.<CategoryDto>builder().success(true).message("Category saved").data(adminService.saveCategory(category)).build();
    }

    @PostMapping("/departments")
    public ApiResponse<DepartmentDto> saveDepartment(@RequestBody Department department) {
        return ApiResponse.<DepartmentDto>builder().success(true).message("Department saved").data(adminService.saveDepartment(department)).build();
    }

    @PostMapping("/venues")
    public ApiResponse<VenueDto> saveVenue(@RequestBody Venue venue) {
        return ApiResponse.<VenueDto>builder().success(true).message("Venue saved").data(adminService.saveVenue(venue)).build();
    }

    @GetMapping("/users/export")
    public ResponseEntity<byte[]> exportUsers() {
        byte[] csv = adminService.exportUsersCsv().getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv").body(csv);
    }
    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }
}

