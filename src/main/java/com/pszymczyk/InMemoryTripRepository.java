package com.pszymczyk;

import java.util.HashMap;
import java.util.Map;

class InMemoryTripRepository implements TripRepository {

    private final Map<String, Trip> storage = new HashMap<>();

    @Override
    public Trip findTrip(String tripCode) {
        return storage.get(tripCode);
    }

    @Override
    public void save(Trip trip) {
        storage.put(trip.getTripCode(), trip);
    }
}
