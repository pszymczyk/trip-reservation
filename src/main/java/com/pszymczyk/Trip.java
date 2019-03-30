package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Trip {

    private final int seatsNumber;

    private List<Reservation> reservations;

    Trip(int seatsNumber) {
        this(seatsNumber, new ArrayList<>());
    }

    Trip(int seatsNumber, List<Reservation> reservations) {
        this.seatsNumber = seatsNumber;
        this.reservations = reservations;
    }

    Optional<ReservationSummary> requestReservation(String userId, String tripCode) {
        if (!hasFreeSeats()) {
            return Optional.empty();
        }

        addReservation(new Reservation(userId, tripCode));
        return Optional.of(new ReservationSummary());
    }

    private boolean hasFreeSeats() {
        List<Reservation> reservations = this.reservations
                                             .stream()
                                             .filter(Reservation::isConfirmed)
                                             .collect(Collectors.toList());

        return seatsNumber > reservations.size();
    }

    private void addReservation(Reservation newReservation) {
        List<Reservation> newReservations = new ArrayList<>(reservations);
        newReservations.add(newReservation);
        reservations = newReservations;
    }

    List<Reservation> getReservations() {
        return reservations;
    }
}
