package com.airport.airport_api.repository;

import com.airport.airport_api.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends MongoRepository<Airport,String> {
    boolean existsByCityName(String cityName);}
