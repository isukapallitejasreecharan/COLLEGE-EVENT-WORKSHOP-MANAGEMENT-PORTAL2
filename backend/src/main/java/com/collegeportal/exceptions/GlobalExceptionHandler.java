package com.collegeportal.exceptions;

import com.collegeportal.dto.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({BadRequestException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleBadRequest(Exception ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleUnauthorized(UnauthorizedException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleDenied(AccessDeniedException ex) {
        return build(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> payload = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        payload.put("timestamp", LocalDateTime.now());
        payload.put("errors", errors);
        return ResponseEntity.badRequest().body(ApiResponse.<Map<String, Object>>builder()
                .success(false)
                .message("Validation failed")
                .data(payload)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleGeneric(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ApiResponse<Map<String, Object>>> build(HttpStatus status, String message) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("timestamp", LocalDateTime.now());
        payload.put("status", status.value());
        return ResponseEntity.status(status).body(ApiResponse.<Map<String, Object>>builder()
                .success(false)
                .message(message)
                .data(payload)
                .build());
    }
}

