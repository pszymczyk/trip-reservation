package com.pszymczyk.domain.model;

public interface TripRepository {

    Trip findTrip(String tripCode);

    void save(Trip trip);
}
