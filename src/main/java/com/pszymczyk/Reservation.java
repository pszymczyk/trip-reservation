package com.pszymczyk;

import java.util.UUID;

class Reservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED
    }

    final UUID id;
    final String userId;
    final ReservationStatus status;

    Reservation(String userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.status = ReservationStatus.NEW;
    }
    Reservation(UUID id, String userId, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
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
