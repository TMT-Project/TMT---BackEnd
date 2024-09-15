package com.tmtbackend.repo;

import com.tmtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByOtp(String otp);
}
