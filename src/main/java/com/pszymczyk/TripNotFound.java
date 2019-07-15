package com.pszymczyk;

class TripNotFound extends RuntimeException {

    public TripNotFound(String tripCode) {
        super("Trip with id " + tripCode + " not found.");
    }
}
