package com.tmtbackend.controller;

import com.tmtbackend.model.RegisterTrip;
import com.tmtbackend.model.User;
import com.tmtbackend.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

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


}
