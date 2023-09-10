package com.airport.airport_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String id;
    private String departureAirport;
    private String arrivalAirport;
    private Instant departureDateTime;
    private Instant returnDateTime;
    private double price;

}
