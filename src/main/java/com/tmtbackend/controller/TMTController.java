package com.tmtbackend.controller;

import com.tmtbackend.model.Airports;
import com.tmtbackend.model.Countries;
import com.tmtbackend.model.FlightDetails;
import com.tmtbackend.model.RegisterTrip;
import com.tmtbackend.service.TmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tmt/")
public class TMTController {


    private final TmtService tmtService;

    @GetMapping("/countries")
    public List<Countries> getCountries() {
        return tmtService.getCountries();
    }

    @GetMapping("/airports")
    public List<Airports> getInternationalAirportsInIndia(){
        return tmtService.getInternationalAirportsInIndia();
    }

    @GetMapping("/getFlightByNum")
    public List<FlightDetails> getFlightByNum(@RequestParam String flightNum,
                                        @RequestParam LocalDate localDate) throws IOException, InterruptedException {
        return tmtService.getFlightByNum(flightNum, localDate);
    }

    @GetMapping("/getActiveTrips")
    public List<RegisterTrip> getActiveTrips(){
        return tmtService.getAllActiveTrips();
    }
}
