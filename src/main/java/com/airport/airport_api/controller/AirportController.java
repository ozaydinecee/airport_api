package com.airport.airport_api.controller;

import com.airport.airport_api.exception.AirportNotFoundException;
import com.airport.airport_api.model.Airport;
import com.airport.airport_api.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airport")
public class AirportController {
    private final Logger logger= LoggerFactory.getLogger(AirportController.class);
    private final AirportService airportService;

    @GetMapping
    public ResponseEntity <List<Airport>> getAllAirports(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.createAirport(airport));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable String id){
        Airport currentAirport=airportService.getAirportById(id);
     if(currentAirport !=null){
        return ResponseEntity.ok(currentAirport);
    }
        return ResponseEntity.notFound().build();
}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable String id, @RequestBody Airport airport) throws AirportNotFoundException {
        try {
            Airport updatedAirport = airportService.updateAirport(id, airport);
            return ResponseEntity.ok(updatedAirport);
        } catch (AirportNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airport with ID: " + id + " not found!");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable String id) {
        return ResponseEntity.ok(airportService.deleteAirport(id));
    }

}
