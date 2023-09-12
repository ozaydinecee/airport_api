package com.airport.airport_api.jobs;

import com.airport.airport_api.model.Flight;
import com.airport.airport_api.service.FlightService;
import com.airport.airport_api.utils.CronExpressionUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static com.airport.airport_api.utils.AirportCityUtil.TURKISH_AIRPORT_CITIES;

@Component
@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enabled",matchIfMissing = true)
@RequiredArgsConstructor
public class FlightInsertJob {
    private final Logger logger = LoggerFactory.getLogger(FlightInsertJob.class);
    private final FlightService flightService;
    @Scheduled(cron = CronExpressionUtil.EVERY_THREE_HOURS)
    public void generateRandomFlight() {
        logger.info("Flight insert job started!");

        Random random = new Random();
        int randomDays = random.nextInt(9) + 2;

        String departureAirport = TURKISH_AIRPORT_CITIES[random.nextInt(TURKISH_AIRPORT_CITIES.length)];
        String arrivalAirport;
        do {
            arrivalAirport = TURKISH_AIRPORT_CITIES[random.nextInt(TURKISH_AIRPORT_CITIES.length)];
        } while (arrivalAirport.equals(departureAirport));
        Instant departureDateTime = Instant.now().plusSeconds(random.nextInt(31536000)); // 1 year in seconds
        departureDateTime=departureDateTime.plus(randomDays, ChronoUnit.DAYS);
        Instant returnDateTime = departureDateTime.plus(randomDays, ChronoUnit.DAYS);
        double price = 100 + random.nextDouble() * 900;

        Flight flight = new Flight();
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureDateTime(departureDateTime);
        flight.setReturnDateTime(returnDateTime);
        flight.setPrice(price);

       flightService.createFlight(flight);
        logger.info("Flight insert job finished!");

    }
}
