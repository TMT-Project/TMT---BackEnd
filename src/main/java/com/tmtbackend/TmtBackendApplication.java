package com.tmtbackend;

import com.tmtbackend.model.Role;
import com.tmtbackend.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class TmtBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmtBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepo roleRepo){
        return args -> {
            if (roleRepo.findByName("USER").isEmpty()){
                roleRepo.save(
                        Role.builder()
                                .name("USER").build()
                );
            }
        };
    }
}
