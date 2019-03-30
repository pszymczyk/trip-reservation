package com.pszymczyk;

import java.util.UUID;

class Reservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED
    }

    private final UUID id;
    private final String userId;
    private final String tripCode;
    private final ReservationStatus status;

    Reservation(String userId, String tripCode) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.tripCode = tripCode;
        this.status = ReservationStatus.NEW;
    }
    private Reservation(UUID id, String userId, String tripCode, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.tripCode = tripCode;
        this.status = status;
    }
    Reservation cancel() {
        return new Reservation(
                this.id,
                this.userId,
                this.tripCode,
                ReservationStatus.CANCELED);
    }

    Reservation confirm() {
        return new Reservation(
                this.id,
                this.userId,
                this.tripCode,
                ReservationStatus.CONFIRMED);
    }

    boolean isConfirmed() {
        return status == Reservation.ReservationStatus.CONFIRMED;
    }
}
