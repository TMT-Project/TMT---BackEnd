package com.tmtbackend.service;

import com.tmtbackend.config.JwtService;
import com.tmtbackend.model.AuthenticationRequest;
import com.tmtbackend.model.AuthenticationResponse;
import com.tmtbackend.model.RegistrationRequest;
import com.tmtbackend.model.User;
import com.tmtbackend.repo.RoleRepo;
import com.tmtbackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final OtpService otpService;

    public void register(RegistrationRequest request) {
        String otp = emailService.generateOTP();
        var userRole = roleRepo.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));
        var user = User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(userRole))
                .mobileNo(request.getMobileNo())
                .nationality(request.getNationality())
                .isEmailVerified(false)
                .otp(otp)
                .otpExpiry(LocalDateTime.now().plusMinutes(5))
                .build();
        userRepo.save(user);
        emailService.verifyEmail(user.getEmail(),user.getOtp());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName",user.getFullname());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public String verifyToken(String otp,String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();

            if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())){
                user.setOtp(null);
                user.setOtpExpiry(null);
                user.setEmailVerified(true);
                userRepo.save(user);
                return "Email verified successfully!";
            }else {
                return "Invalid or expired OTP!";
            }
        }else {
            return "User not found!";
        }
    }

    public String forgotPassword(String email) {
        try{
            otpService.generateSendOtp(email);
            return "OTP has been sent to your email.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public boolean verifyForgotOtp(String otp, String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return user.getOtp().equals(otp)&&user.getOtpExpiry().isAfter(LocalDateTime.now());
        }
        return false;
    }

    public void resetPassword(String email, String newPassword) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setOtp(null);
            user.setOtpExpiry(null);
            userRepo.save(user);
        }else {
            throw new RuntimeException("User not found");
        }
    }
}
