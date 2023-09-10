package com.airport.airport_api;

import com.airport.airport_api.model.Airport;
import com.airport.airport_api.model.Flight;
import com.airport.airport_api.repository.AirportRepository;
import com.airport.airport_api.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
public class AirportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportApiApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner runner(AirportRepository airportRepository) {
        return args -> {
            Airport airport = new Airport();
            airport.setCityName("ADANA");
            airport.setId(String.valueOf(UUID.randomUUID()));

            airportRepository.save(airport);

        };*/
    }
