package com.pszymczyk;

import java.util.UUID;

record Reservation(UUID id, String userId, ReservationStatus status) {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED
    }

    Reservation(String userId) {
        this(UUID.randomUUID(), userId, ReservationStatus.NEW);
    }

    Reservation cancel() {
        return new Reservation(
                this.id,
                this.userId,
                ReservationStatus.CANCELED);
    }

    Reservation confirm() {
        return new Reservation(
                this.id,
                this.userId,
                ReservationStatus.CONFIRMED);
    }

    boolean isConfirmed() {
        return status == Reservation.ReservationStatus.CONFIRMED;
    }

}
