package com.pszymczyk;

import java.util.Optional;

class TripService {

    private final TripRepository tripRepository;
    private EventPublisher eventPublisher;

    TripService(TripRepository tripRepository, EventPublisher eventPublisher) {
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
