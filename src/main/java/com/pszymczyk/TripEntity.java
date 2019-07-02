package com.pszymczyk;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import static java.util.stream.Collectors.toMap;

@Entity
class TripEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String tripCode;

    @OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="TRIP_ID") // join column is in table for Order
    private Set<ReservationEntity> reservations;

    private int seatsNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public void update(Trip trip) {
        Map<UUID, Reservation> grouped = trip.getReservations()
                                             .stream()
                                             .collect(toMap(x -> x.id, x -> x));

        for (ReservationEntity reservationEntity: reservations) {
            Reservation reservation = grouped.get(reservationEntity.getId());
            reservationEntity.setStatus(reservation.status.name());
            grouped.remove(reservation.id);
        }

        for (Reservation reservation: grouped.values()) {
            ReservationEntity reservationEntity = new ReservationEntity();
            reservationEntity.setId(reservation.id);
            reservationEntity.setUserId(reservation.userId);
            reservationEntity.setStatus(reservation.status.name());
            reservations.add(reservationEntity);
        }
    }
}
