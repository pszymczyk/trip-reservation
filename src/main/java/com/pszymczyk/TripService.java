package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TripService {

    private final TripDao tripDao;

    public TripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public void book(String userId, String tripCode) {
        Trip trip = tripDao.findTrip(tripCode);
        List<Reservation> reservations = trip.getReservations()
                                             .stream()
                                             .filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED)
                                             .collect(Collectors.toList());

        if (trip.getSeatsNumber() <= reservations.size()) {
            throw new IllegalStateException();
        }

        List<Reservation> newReservations = new ArrayList<>(trip.getReservations());
        newReservations.add(newReservation(userId, tripCode));
        trip.setReservations(newReservations);
    }

    private Reservation newReservation(String userId, String tripCode) {
        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setUserId(userId);
        reservation.setTripCode(tripCode);
        reservation.setStatus(Reservation.ReservationStatus.NEW);
        return reservation;
    }
}
