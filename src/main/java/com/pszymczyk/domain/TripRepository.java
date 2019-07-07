package com.pszymczyk.domain;

public interface TripRepository {

    Trip findTrip(String tripCode);

    void save(Trip trip);
}
