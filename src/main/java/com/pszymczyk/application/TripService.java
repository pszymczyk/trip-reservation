package com.pszymczyk.application;

import java.util.Optional;

import com.pszymczyk.domain.ReservationSummary;
import com.pszymczyk.domain.Trip;
import com.pszymczyk.domain.TripRepository;

public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);

        Optional<ReservationSummary> reservationSummary = trip.requestReservation(userId);

        if (!reservationSummary.isPresent()) {
            throw new IllegalStateException();
        }

        tripRepository.save(trip);
    }
}
