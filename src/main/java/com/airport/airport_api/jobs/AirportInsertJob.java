package com.airport.airport_api.jobs;

import com.airport.airport_api.exception.AirportNameAlreadyExistsException;
import com.airport.airport_api.model.Airport;
import com.airport.airport_api.service.AirportService;
import com.airport.airport_api.utils.CronExpressionUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

import static com.airport.airport_api.utils.AirportCityUtil.TURKISH_AIRPORT_CITIES;

@Component
@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enabled",matchIfMissing = true)
@RequiredArgsConstructor
public class AirportInsertJob {
    private final Logger logger = LoggerFactory.getLogger(AirportInsertJob.class);
    private final AirportService airportService;
    @Scheduled(cron = CronExpressionUtil.EVERY_THREE_HOURS)
    public void insertAirportToDB() throws AirportNameAlreadyExistsException {
        logger.info("Airport insert job started!");
        Random random=new Random();
        String randomCity = TURKISH_AIRPORT_CITIES[random.nextInt(TURKISH_AIRPORT_CITIES.length)];
        Airport airport = new Airport();
        airport.setCityName(randomCity);
        airport.setId(UUID.randomUUID().toString());

        airportService.createAirport(airport);
        logger.info("Airport inserted to db with id: "+airport.getId()+" and airtport city name: "+airport.getCityName());
        logger.info("Airport insert job finished!");
   }
}
