package com.airport.airport_api.controller;

import com.airport.airport_api.exception.FlightNotFoundException;
import com.airport.airport_api.model.Flight;
import com.airport.airport_api.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {

        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable String id) {
        Flight currentFlight = flightService.getFlightById(id);
        if (currentFlight != null) {
            return ResponseEntity.ok(currentFlight);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable String id, @RequestBody Flight flight) throws FlightNotFoundException {
        try {
            Flight updatedFlight = flightService.updateFlight(id, flight);
            return ResponseEntity.ok(updatedFlight);
        } catch (FlightNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with ID: " + id + " not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable String id) {
       return ResponseEntity.ok(flightService.deleteFlight(id));
    }
}
