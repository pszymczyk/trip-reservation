package com.pszymczyk.application;

import com.pszymczyk.domain.model.EventPublisher;
import com.pszymczyk.domain.model.ReservationSummary;
import com.pszymczyk.domain.model.Trip;
import com.pszymczyk.domain.model.TripFullyBooked;
import com.pszymczyk.domain.model.TripNotFound;
import com.pszymczyk.domain.model.TripRepository;

public class TripService {

    private final TripRepository tripRepository;
    private final EventPublisher eventPublisher;

    TripService(TripRepository tripRepository, EventPublisher eventPublisher) {
        this.tripRepository = tripRepository;
        this.eventPublisher = eventPublisher;
    }

    ReservationSummary book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);
        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        return trip.requestReservation(userId, eventPublisher)
                   .map(summary -> {
                       tripRepository.save(trip);
                       return summary;
                   })
                   .orElseThrow(() -> new TripFullyBooked(tripCode));
    }
}
