package com.airport.airport_api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AirportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportApiApplication.class, args);
    }
/*
    @Bean
    CommandLineRunner runner(AirportRepository airportRepository, FlightRepository flightRepository){
        return args ->{
            Flight flight=new Flight(
                    String.valueOf(UUID.randomUUID()),
                    "ADANA",
                    "İSTANBUL",
                    Instant.now(),
                    Instant.now(),
                    10.6
                    );
            flightRepository.save(flight);

        };
    }

    @Bean
    CommandLineRunner runner(AirportRepository airportRepository) {
        return args -> {
            Airport airport = new Airport();
            airport.setCityName("ADANA");
            airport.setId(String.valueOf(UUID.randomUUID()));

            airportRepository.save(airport);

        };

    @Bean
    CommandLineRunner runner(UserRepository userRepository){
        return args ->{
            User user=new User();
            user.setId(UUID.randomUUID());
            user.setEmail("ozaydinecee@gmail.com");
            user.setPassword("ece123");
            user.setFirstName("ece");
            user.setLastName("ozaydin");
            user.setRole(Role.ADMIN);

            userRepository.save(user);

        };*/
    }
