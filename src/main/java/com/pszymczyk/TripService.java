package com.pszymczyk;

class TripService {

    private final TripRepository tripRepository;

    TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    ReservationSummary book(String userId, String tripCode) {
        var trip = tripRepository.findTrip(tripCode);
        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        return trip.requestReservation(userId)
                   .map(summary -> {
                       tripRepository.save(trip);
                       return summary;
                   })
                   .orElseThrow(() -> new TripFullyBooked(tripCode));
    }
}
