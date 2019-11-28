package com.pszymczyk.application;

import java.util.Optional;

import com.pszymczyk.domain.model.EventPublisher;
import com.pszymczyk.domain.model.ReservationSummary;
import com.pszymczyk.domain.model.Trip;
import com.pszymczyk.domain.model.TripFullyBooked;
import com.pszymczyk.domain.model.TripNotFound;
import com.pszymczyk.domain.model.TripRepository;

public class TripService {

    private final TripRepository tripRepository;
    private final EventPublisher eventPublisher;

    public TripService(TripRepository tripRepository, EventPublisher eventPublisher) {
        this.tripRepository = tripRepository;
        this.eventPublisher = eventPublisher;
    }

    public void book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);
        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        Optional<ReservationSummary> reservationSummary = trip.requestReservation(userId, eventPublisher);

        if (!reservationSummary.isPresent()) {
            throw new TripFullyBooked(tripCode);
        }
        tripRepository.save(trip);
    }
}
