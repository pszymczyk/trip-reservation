package com.pszymczyk;

import java.util.UUID;

class TripService {

    private final TripRepository tripRepository;

    TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    ReservationSummary book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);

        if (trip == null) {
            throw new TripNotFound(tripCode);
        }

        if (trip.getReservations().stream().filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED).count() >= trip.getSeats()) {
            throw new TripFullyBooked(tripCode);
        }

        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setStatus(Reservation.ReservationStatus.NEW);
        reservation.setUserId(userId);
        trip.getReservations().add(reservation);

        ReservationSummary reservationSummary = new ReservationSummary();
        reservationSummary.setStatus(Reservation.ReservationStatus.NEW.toString());
        reservationSummary.setReservationId(reservation.getId().toString());

        return reservationSummary;
    }
}