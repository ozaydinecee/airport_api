package com.airport.airport_api.exception;

public class AirportNameAlreadyExistsException extends Throwable {
    public AirportNameAlreadyExistsException(String message) {
        super(message);
    }
}
