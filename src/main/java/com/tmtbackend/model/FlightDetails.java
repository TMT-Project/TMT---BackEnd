package com.tmtbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetails {
    
    private GreatCircleDistance greatCircleDistance;
    private Departure departure;
    private Arrival arrival;
    private String lastUpdatedUtc;
    private String number;
    private String status;
    private String codeshareStatus;
    private boolean isCargo;
    private Aircraft aircraft;
    private Airline airline;

    // Getters and Setters

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GreatCircleDistance {
        private double meter;
        private double km;
        private double mile;
        private double nm;
        private double feet;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Departure {
        private Airport airport;
        private ScheduledTime scheduledTime;
        private String terminal;
        private List<String> quality;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Arrival {
        private Airport airport;
        private ScheduledTime scheduledTime;
        private PredictedTime predictedTime;
        private String terminal;
        private List<String> quality;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Airport {
        private String icao;
        private String iata;
        private String name;
        private String shortName;
        private String municipalityName;
        private Location location;
        private String countryCode;
        private String timeZone;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location {
        private double lat;
        private double lon;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScheduledTime {
        private String utc;
        private String local;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PredictedTime {
        private String utc;
        private String local;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Aircraft {
        private String model;

        // Getters and Setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Airline {
        private String name;
        private String iata;
        private String icao;

        // Getters and Setters
    }
}
