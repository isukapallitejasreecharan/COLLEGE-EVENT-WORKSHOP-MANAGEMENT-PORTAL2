package com.collegeportal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI portalOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("College Event & Workshop Management Portal API")
                        .version("v1")
                        .description("Production-style API for college events, workshops, certificates, attendance, and notifications")
                        .contact(new Contact().name("Portal Team").email("support@collegeportal.local"))
                        .license(new License().name("MIT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .schemaRequirement("bearerAuth", new SecurityScheme()
                        .name("bearerAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }
}

