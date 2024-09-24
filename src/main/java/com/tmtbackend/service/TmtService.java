package com.tmtbackend.service;

import com.tmtbackend.model.Airports;
import com.tmtbackend.model.Countries;
import com.tmtbackend.model.FlightDetails;
import com.tmtbackend.repo.AirportsRepo;
import com.tmtbackend.repo.CountriesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TmtService {

    private final CountriesRepo countriesRepo;
    private final AirportsRepo airportsRepo;

    public List<Countries> getCountries() {
        log.info(String.valueOf(countriesRepo.count()));
        return countriesRepo.findAll();
    }

    public List<Airports> getInternationalAirportsInIndia() {
        List<String> types = new ArrayList<>();
        types.add("large_airport");
        types.add("medium_airport");
        log.info(String.valueOf(airportsRepo.findAllByTypeIn(types).size()));
        return airportsRepo.findAllByTypeIn(types);
    }

    public FlightDetails getFlightByNum(String flightNum, LocalDate localDate) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://aerodatabox.p.rapidapi.com/flights/number/"+flightNum+"/"+localDate+"?withAircraftImage=false&withLocation=false"))
                .header("x-rapidapi-key", "95c6521fbfmsh9cf5be1c44333c7p1c4896jsn34d81033e9ed")
                .header("x-rapidapi-host", "aerodatabox.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return null;
    }

}
