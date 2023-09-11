package com.airport.airport_api.controller;

import com.airport.airport_api.model.Flight;
import com.airport.airport_api.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final FlightService flightService;
    @GetMapping("/search")
    public List<?> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Instant departureDateTime,
            @RequestParam(required = false) Instant returnDateTime) {
        List<Flight> flights=flightService.searchFlights(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
        if (flights.isEmpty()) {
            return Collections.singletonList(ResponseEntity.notFound().build());
        }

        return ResponseEntity.ok(flights).getBody();
    }
}
