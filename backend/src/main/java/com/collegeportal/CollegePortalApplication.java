package com.collegeportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class CollegePortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegePortalApplication.class, args);
    }
}

