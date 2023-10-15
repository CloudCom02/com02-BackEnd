package com._02server.com02backendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Com02BackendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Com02BackendProjectApplication.class, args);
    }

}
