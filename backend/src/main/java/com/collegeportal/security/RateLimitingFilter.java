package com.collegeportal.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Window> buckets = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (!path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        String key = request.getRemoteAddr() + ':' + path;
        Window window = buckets.computeIfAbsent(key, unused -> new Window(Instant.now().plusSeconds(60).toEpochMilli(), 0));
        synchronized (window) {
            long now = Instant.now().toEpochMilli();
            if (now > window.resetAt) {
                window.resetAt = Instant.now().plusSeconds(60).toEpochMilli();
                window.count = 0;
            }
            if (window.count >= 15) {
                response.setStatus(429);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write("{\"success\":false,\"message\":\"Too many requests\",\"data\":null}");
                return;
            }
            window.count++;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return true;
    }

    private static final class Window {
        private long resetAt;
        private int count;

        private Window(long resetAt, int count) {
            this.resetAt = resetAt;
            this.count = count;
        }
    }
}

