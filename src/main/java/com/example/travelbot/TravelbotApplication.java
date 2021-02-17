package com.example.travelbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelbotApplication.class, args);
    }

}
