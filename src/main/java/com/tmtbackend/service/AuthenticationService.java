package com.tmtbackend.service;

import com.tmtbackend.model.RegistrationRequest;
import com.tmtbackend.model.User;
import com.tmtbackend.repo.RoleRepo;
import com.tmtbackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public void register(RegistrationRequest request) {
        var userRole = roleRepo.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));
        var user = User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(userRole))
                .mobileNo(request.getMobileNo())
                .nationality(request.getNationality())
                .build();
        userRepo.save(user);
    }
}
