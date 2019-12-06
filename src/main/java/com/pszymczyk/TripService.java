package com.pszymczyk;

class TripService {

    private final TripRepository tripRepository;
    private ReservationsReadModel reservationsReadModel;

    TripService(TripRepository tripRepository, ReservationsReadModel reservationsReadModel) {
        this.tripRepository = tripRepository;
        this.reservationsReadModel = reservationsReadModel;
    }

    ReservationSummary book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);
        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        return trip.requestReservation(userId)
                   .map(summary -> {
                       tripRepository.save(trip);
                       reservationsReadModel.update(new ReservationAdded(tripCode, summary.getReservationId(), userId, summary.getStatus()));
                       return summary;
                   })
                   .orElseThrow(() -> new TripFullyBooked(tripCode));
    }
}
