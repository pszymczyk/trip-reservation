package com.pszymczyk;

class TripNotFound extends RuntimeException {

    TripNotFound(String tripCode) {
        super("Trip with id " + tripCode + " not found.");
    }
}
