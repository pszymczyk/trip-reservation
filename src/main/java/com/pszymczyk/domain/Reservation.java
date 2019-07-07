package com.pszymczyk.domain;

import java.util.UUID;

public class Reservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED
    }

    final UUID id;
    final String userId;
    final ReservationStatus status;

    public Reservation(String userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.status = ReservationStatus.NEW;
    }

    public Reservation(UUID id, String userId, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public Reservation cancel() {
        return new Reservation(
                this.id,
                this.userId,
                ReservationStatus.CANCELED);
    }

    public Reservation confirm() {
        return new Reservation(
                this.id,
                this.userId,
                ReservationStatus.CONFIRMED);
    }

    boolean isConfirmed() {
        return status == Reservation.ReservationStatus.CONFIRMED;
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
