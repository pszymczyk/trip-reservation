package com.pszymczyk;

class TripService {

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
