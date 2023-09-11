package com.airport.airport_api.service;

import com.airport.airport_api.exception.AirportNotFoundException;
import com.airport.airport_api.model.Airport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface AirportService {
    Airport createAirport(Airport airport);
    public List<Airport> getAllAirports();
    Airport updateAirport(String id, Airport airport) throws AirportNotFoundException;

    String deleteAirport(String id);

    public Airport getAirportById(String id);


}
