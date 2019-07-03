package com.pszymczyk;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
class TripEntity {

    @Id
    @GeneratedValue
    private long entityId;

    private String tripCode;

    private int seatsNumber;

    @OneToMany(orphanRemoval=true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="TRIP_ID")
    private Set<ReservationEntity> reservations;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
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

    public void apply(Trip trip) {

    }
}
