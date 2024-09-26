package com.tmtbackend.controller;

import com.tmtbackend.config.JwtService;
import com.tmtbackend.model.RegisterTrip;
import com.tmtbackend.model.User;
import com.tmtbackend.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterTrip> registerTrip(@RequestBody RegisterTrip registerTrip,
                                                     Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        registerTrip.setUser(user);
        return ResponseEntity.ok(tripService.registerTrip(registerTrip));
    }

    @GetMapping("getUserTrips")
    public ResponseEntity<List<RegisterTrip>> getUserTrips(Authentication connectUser) {
        User user = (User) connectUser.getPrincipal();
        return ResponseEntity.ok(tripService.getUserTrip(user));
    }

    @PostMapping("/getUserDetails")
    public Optional<User> getUserDetails(@RequestHeader("Authorization") String token){
        if (token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        return jwtService.getUserDetailsFromToken(token);
    }
}
