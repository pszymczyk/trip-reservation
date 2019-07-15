package com.pszymczyk;

class TripFullyBooked extends RuntimeException {

    public TripFullyBooked(String tripCode) {
        super("Trip " + tripCode + "fully booked.");
    }
}
