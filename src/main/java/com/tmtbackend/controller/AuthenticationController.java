package com.tmtbackend.controller;

import com.tmtbackend.model.AuthenticationRequest;
import com.tmtbackend.model.AuthenticationResponse;
import com.tmtbackend.model.RegistrationRequest;
import com.tmtbackend.model.User;
import com.tmtbackend.repo.UserRepo;
import com.tmtbackend.service.AuthenticationService;
import com.tmtbackend.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
//@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepo userRepo;
    private final OtpService otpService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @Valid @RequestBody RegistrationRequest request
    ) {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("otp") String otp, @RequestParam("email") String email) {
        return authenticationService.verifyToken(otp, email);
    }

    @PostMapping("/resendOtp")
    public String resendOtp(@RequestParam("email") String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            otpService.generateSendOtp(email);
            return "OTP has been resent to your email";
        } else {
            return "User not found!";
        }
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam("email") String email) {
        return authenticationService.forgotPassword(email);
    }

    @PostMapping("/verifyForgotOtp")
    public String verifyForgotPasswordOtp(
            @RequestParam("email") String email,
            @RequestParam("otp") String otp
    ){
        if (authenticationService.verifyForgotOtp(otp,email)){
            return "OTP verified successfully!";
        }else {
            return "Invalid or expired OTP!";
        }
    }

    @PostMapping("/resetPassword")
    public String resetPassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword
    ){
        authenticationService.resetPassword(email,newPassword);
        return "Password has been reset successfully!";
    }
}
