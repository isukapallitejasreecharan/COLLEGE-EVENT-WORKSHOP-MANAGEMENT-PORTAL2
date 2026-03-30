package com.collegeportal.config;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

public class RenderDatabaseEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String PROPERTY_SOURCE_NAME = "renderPostgresDatasource";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (!isRenderProfileActive(environment) || hasText(environment.getProperty("spring.datasource.url"))) {
            return;
        }

        Map<String, Object> datasourceProperties = resolveDatasourceProperties(environment);
        if (datasourceProperties.isEmpty()) {
            return;
        }

        environment.getPropertySources().addFirst(new MapPropertySource(PROPERTY_SOURCE_NAME, datasourceProperties));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private Map<String, Object> resolveDatasourceProperties(ConfigurableEnvironment environment) {
        String url = firstNonBlank(environment, "RENDER_DATABASE_URL", "DATABASE_URL", "POSTGRES_URL");
        if (hasText(url)) {
            return buildFromDatabaseUrl(environment, url);
        }

        String host = firstNonBlank(environment, "RENDER_DB_HOST", "PGHOST");
        String database = firstNonBlank(environment, "RENDER_DB_NAME", "PGDATABASE");
        if (!hasText(host) || !hasText(database)) {
            return Map.of();
        }

        String port = firstNonBlank(environment, "RENDER_DB_PORT", "PGPORT");
        String username = firstNonBlank(environment, "RENDER_DB_USERNAME", "PGUSER");
        String password = firstNonBlank(environment, "RENDER_DB_PASSWORD", "PGPASSWORD");

        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("spring.datasource.url",
                "jdbc:postgresql://" + host + ":" + (hasText(port) ? port : "5432") + "/" + database);
        putIfHasText(properties, "spring.datasource.username", username);
        putIfHasText(properties, "spring.datasource.password", password);
        return properties;
    }

    private Map<String, Object> buildFromDatabaseUrl(ConfigurableEnvironment environment, String rawUrl) {
        URI databaseUri;
        try {
            databaseUri = URI.create(rawUrl.trim());
        } catch (IllegalArgumentException ex) {
            return Map.of();
        }

        String scheme = databaseUri.getScheme();
        if (!"postgres".equalsIgnoreCase(scheme) && !"postgresql".equalsIgnoreCase(scheme)) {
            return Map.of();
        }

        String host = databaseUri.getHost();
        String database = databaseUri.getPath();
        if (!hasText(host) || !hasText(database) || "/".equals(database)) {
            return Map.of();
        }

        String jdbcUrl = new StringBuilder("jdbc:postgresql://")
                .append(host)
                .append(':')
                .append(databaseUri.getPort() > 0 ? databaseUri.getPort() : 5432)
                .append(database)
                .append(hasText(databaseUri.getRawQuery()) ? "?" + databaseUri.getRawQuery() : "")
                .toString();

        String[] credentials = parseCredentials(databaseUri.getRawUserInfo());
        String username = firstNonBlank(environment, "RENDER_DB_USERNAME", "PGUSER", "spring.datasource.username");
        String password = firstNonBlank(environment, "RENDER_DB_PASSWORD", "PGPASSWORD", "spring.datasource.password");

        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("spring.datasource.url", jdbcUrl);
        if (!hasText(username)) {
            username = credentials[0];
        }
        if (!hasText(password)) {
            password = credentials[1];
        }
        putIfHasText(properties, "spring.datasource.username", username);
        putIfHasText(properties, "spring.datasource.password", password);
        return properties;
    }

    private String[] parseCredentials(String userInfo) {
        String[] credentials = new String[2];
        if (!hasText(userInfo)) {
            return credentials;
        }

        String[] parts = userInfo.split(":", 2);
        credentials[0] = decode(parts[0]);
        if (parts.length > 1) {
            credentials[1] = decode(parts[1]);
        }
        return credentials;
    }

    private boolean isRenderProfileActive(ConfigurableEnvironment environment) {
        return Arrays.stream(environment.getActiveProfiles())
                .anyMatch(profile -> "render".equalsIgnoreCase(profile));
    }

    private String firstNonBlank(ConfigurableEnvironment environment, String... propertyNames) {
        for (String propertyName : propertyNames) {
            String value = environment.getProperty(propertyName);
            if (hasText(value)) {
                return value.trim();
            }
        }
        return null;
    }

    private void putIfHasText(Map<String, Object> properties, String key, String value) {
        if (hasText(value)) {
            properties.put(key, value);
        }
    }

    private boolean hasText(String value) {
        return StringUtils.hasText(value);
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
