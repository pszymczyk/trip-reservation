package com.pszymczyk;

interface TripRepository {

    Trip findTrip(String tripCode);

    void save(Trip trip);
}
