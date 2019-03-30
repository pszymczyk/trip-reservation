package com.pszymczyk;

import java.util.Optional;

class TripService {

    private final TripDao tripDao;

    TripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    void book(String userId, String tripCode) {
        Trip trip = tripDao.findTrip(tripCode);

        Optional<ReservationSummary> reservationSummary = trip.requestReservation(userId, tripCode);

        if (!reservationSummary.isPresent()) {
            throw new IllegalStateException();
        }
    }
}
