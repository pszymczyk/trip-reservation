package com.pszymczyk;

import java.util.Optional;

class TripService {

    private final TripRepository tripRepository;

    TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    void book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);
        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        Optional<ReservationSummary> reservationSummary = trip.requestReservation(userId);

        if (!reservationSummary.isPresent()) {
            throw new TripFullyBooked(tripCode);
        }

        tripRepository.save(trip);
    }
}
