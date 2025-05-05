package com.microservices.assuranceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.microservices.assuranceservice",
        "com.microservices.controller",
        "com.microservices.config",
        "com.microservices.dto",
        "com.microservices.exceptions",
})
public class AssuranceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceServiceApplication.class, args);
    }

}
