package com.tmtbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TmtBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmtBackendApplication.class, args);
    }

}
