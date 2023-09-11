package com.airport.airport_api.service;

import com.airport.airport_api.exception.AirportNotFoundException;
import com.airport.airport_api.exception.FlightNotFoundException;
import com.airport.airport_api.model.Flight;
import com.airport.airport_api.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(String id) {
        Optional<Flight> optionalFlight=flightRepository.findById(id);
        return optionalFlight.orElse(null);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(String id, Flight flight) throws AirportNotFoundException {
        Optional <Flight> optionalFlight=flightRepository.findById(id);
        if(optionalFlight.isPresent()){
            Flight currentFlight=optionalFlight.get();
            currentFlight.setDepartureAirport(flight.getDepartureAirport());
            currentFlight.setArrivalAirport(flight.getArrivalAirport());
            currentFlight.setDepartureDateTime(flight.getDepartureDateTime());
            currentFlight.setReturnDateTime(flight.getReturnDateTime());
            currentFlight.setPrice(flight.getPrice());
            return flightRepository.save(currentFlight);
        }
            throw new FlightNotFoundException("Flight with ID:"+ id + " not found!");

    }

    @Override
    public String deleteFlight(String id) {
        Optional<Flight> optionalFlight=flightRepository.findById(id);
        if(optionalFlight.isPresent()){
            flightRepository.deleteById(id);
            return "Flight deleted successfully";
        }
        return "Flight not found";
    }
}
