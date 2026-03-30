package com.collegeportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EntityScan(basePackages = "com.collegeportal.entities")
@EnableJpaRepositories(basePackages = "com.collegeportal.repositories")
@SpringBootApplication(scanBasePackages = {
        "com.collegeportal.config",
        "com.collegeportal.controllers",
        "com.collegeportal.exceptions",
        "com.collegeportal.mappers",
        "com.collegeportal.security",
        "com.collegeportal.services",
        "com.collegeportal.utils"
})
public class CollegePortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegePortalApplication.class, args);
    }
}
