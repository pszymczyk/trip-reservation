package com.pszymczyk.domain.model;

public class TripFullyBooked extends RuntimeException {

    public TripFullyBooked(String tripCode) {
        super("Trip " + tripCode + "fully booked.");
    }
}
