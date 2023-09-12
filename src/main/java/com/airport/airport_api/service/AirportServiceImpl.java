package com.airport.airport_api.service;

import com.airport.airport_api.exception.AirportNameAlreadyExistsException;
import com.airport.airport_api.exception.AirportNotFoundException;
import com.airport.airport_api.model.Airport;
import com.airport.airport_api.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    @Override
    public Airport createAirport(Airport airport) throws AirportNameAlreadyExistsException {
        if(!airportRepository.existsByCityName(airport.getCityName())){
            return airportRepository.save(airport);
        }
        throw new AirportNameAlreadyExistsException("Airport already exist with name: " + airport.getCityName());
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport updateAirport(String id, Airport airport) throws AirportNotFoundException {
        Optional<Airport> optionalAirport=airportRepository.findById(id);
        if(optionalAirport.isPresent()){
            Airport currentAirport=optionalAirport.get();
            currentAirport.setCityName(airport.getCityName());
            return airportRepository.save(currentAirport);
        }
        throw new AirportNotFoundException("Airport with ID:"+ id + " not found!");
    }

    @Override
    public String deleteAirport(String id) {
        Optional<Airport> optionalAirport=airportRepository.findById(id);
        if(optionalAirport.isPresent()){
            airportRepository.deleteById(id);
            return "Airport deleted successfully";
        }
        return "Airport not found";
    }

    @Override
    public Airport getAirportById(String id) {
       Optional<Airport> optionalAirport=airportRepository.findById(id);
       return optionalAirport.orElse(null);
    }
}
