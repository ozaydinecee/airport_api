package com.airport.airport_api.repository;

import com.airport.airport_api.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight,String> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeGreaterThanEqual(
            String departureAirport, String arrivalAirport, Instant departureDateTime);
}
