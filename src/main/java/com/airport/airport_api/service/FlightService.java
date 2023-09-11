package com.airport.airport_api.service;

import com.airport.airport_api.exception.FlightNotFoundException;
import com.airport.airport_api.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    Flight createFlight(Flight flight);
    Flight getFlightById(String id);
    List<Flight> getAllFlights();
    Flight updateFlight(String id, Flight flight) throws FlightNotFoundException;
    String deleteFlight(String id);
}
