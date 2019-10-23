package com.pszymczyk;

import java.util.Optional;

class TripService {

    private final TripRepository tripRepository;
    private final ReservationsReadModel reservationsReadModel;

    TripService(TripRepository tripRepository, ReservationsReadModel reservationsReadModel) {
        this.tripRepository = tripRepository;
        this.reservationsReadModel = reservationsReadModel;
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
        reservationSummary.ifPresent(summary -> reservationsReadModel.update(
                new ReservationAdded(tripCode, summary.getReservationId(), userId, summary.getStatus())));
    }
}
