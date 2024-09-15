package com.tmtbackend.service;

import com.tmtbackend.model.User;
import com.tmtbackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final UserRepo userRepo;
    private final EmailService emailService;

    public void generateSendOtp(String email){
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            String otp = emailService.generateOTP();
            user.setOtp(otp);
            user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
            userRepo.save(user);
            emailService.verifyEmail(email,otp);
        }else {
            throw new RuntimeException("User not Found");
        }
    }
}
