package com.pszymczyk;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
class TripService {

    private final TripRepository tripRepository;

    TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    void addTrip(String tripCode, int seatsNumber) {
        tripRepository.save(new Trip(tripCode, seatsNumber));
    }

    void book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);

        Optional<ReservationSummary> reservationSummary = trip.requestReservation(userId);

        if (!reservationSummary.isPresent()) {
            throw new IllegalStateException();
        }

        tripRepository.save(trip);
    }
}
