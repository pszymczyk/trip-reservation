package com.pszymczyk;

class ReservationSummary implements DomainEvent {

    private final String reservationId;
    private final String status;

    ReservationSummary(String reservationId, String status) {
        this.reservationId = reservationId;
        this.status = status;
    }

    String getReservationId() {
        return reservationId;
    }

    String getStatus() {
        return status;
    }
}
