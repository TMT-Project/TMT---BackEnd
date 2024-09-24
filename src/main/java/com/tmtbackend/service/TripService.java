package com.tmtbackend.service;

import com.tmtbackend.model.RegisterTrip;
import com.tmtbackend.model.User;
import com.tmtbackend.repo.RegisterTripRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final RegisterTripRepo registerTripRepo;

    public RegisterTrip registerTrip(RegisterTrip registerTrip) {
        return registerTripRepo.save(registerTrip);
    }

    public List<RegisterTrip> getUserTrip(User user) {
        return registerTripRepo.findAllById(Collections.singleton(user.getId()));
    }


}
