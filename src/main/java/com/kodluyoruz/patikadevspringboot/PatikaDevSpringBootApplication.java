package com.kodluyoruz.patikadevspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PatikaDevSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatikaDevSpringBootApplication.class, args);
    }

}

