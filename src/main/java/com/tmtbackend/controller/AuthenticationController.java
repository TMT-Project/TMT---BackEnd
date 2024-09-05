package com.tmtbackend.controller;

import com.tmtbackend.model.RegistrationRequest;
import com.tmtbackend.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
//@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @Valid @RequestBody RegistrationRequest request
    ){
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }
}
