package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class P145121Application {

    public static void main(String[] args) {
        SpringApplication.run(P145121Application.class, args);
    }

}
