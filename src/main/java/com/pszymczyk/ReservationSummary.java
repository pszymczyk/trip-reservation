package com.pszymczyk;

class ReservationSummary implements DomainEvent {

    private final String reservationId;
    private final String status;

    public ReservationSummary(String reservationId, String status) {
        this.reservationId = reservationId;
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getStatus() {
        return status;
    }
}
