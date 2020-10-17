package com.titanic.fork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ForkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForkApplication.class, args);
    }
}
