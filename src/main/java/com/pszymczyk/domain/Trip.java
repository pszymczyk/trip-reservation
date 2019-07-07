package com.pszymczyk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Trip {

    private final String tripCode;
    private final int seatsNumber;

    private List<Reservation> reservations;

    public Trip(String tripCode, int seatsNumber) {
        this(tripCode, seatsNumber, new ArrayList<>());
    }

    public Trip(String tripCode, int seatsNumber, List<Reservation> reservations) {
        this.tripCode = tripCode;
        this.seatsNumber = seatsNumber;
        this.reservations = reservations;
    }

    public Optional<ReservationSummary> requestReservation(String userId) {
        if (!hasFreeSeats()) {
            return Optional.empty();
        }

        addReservation(new Reservation(userId));
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

    public String getTripCode() {
        return tripCode;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
