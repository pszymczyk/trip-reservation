package com.pszymczyk;

import org.springframework.stereotype.Component;

@Component
class SqlTripRepository implements TripRepository {

    @Override
    public Trip findTrip(String tripCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Trip trip) {
        throw new UnsupportedOperationException();
    }
}
