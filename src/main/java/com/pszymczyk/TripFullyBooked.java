package com.pszymczyk;

class TripFullyBooked extends RuntimeException {

    TripFullyBooked(String tripCode) {
        super("Trip " + tripCode + "fully booked.");
    }
}
