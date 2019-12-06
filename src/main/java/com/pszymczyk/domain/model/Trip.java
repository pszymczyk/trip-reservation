package com.pszymczyk.domain.model;

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

    public Optional<ReservationSummary> requestReservation(String userId, EventPublisher eventPublisher) {
        if (!hasFreeSeats()) {
            return Optional.empty();
        }

        Reservation newReservation = new Reservation(userId);
        addReservation(newReservation);
        eventPublisher.send(new ReservationAdded(tripCode, newReservation.getId().toString(), userId, newReservation.getStatus().name()));
        return Optional.of(new ReservationSummary(newReservation.getId().toString(), newReservation.getStatus().name()));
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public String getTripCode() {
        return tripCode;
    }
}
