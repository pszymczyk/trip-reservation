package com.pszymczyk;

import java.util.UUID;

class Reservation {

    enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED
    }

    private final UUID id;
    private final String userId;
    private final ReservationStatus status;

    Reservation(String userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.status = ReservationStatus.NEW;
    }

    private Reservation(UUID id, String userId, ReservationStatus status) {
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
