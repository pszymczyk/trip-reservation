package com.pszymczyk.domain.model;

public class ReservationSummary {

    private final String reservationId;
    private final String status;

    public ReservationSummary(String reservationId, String status) {
        this.reservationId = reservationId;
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    String getStatus() {
        return status;
    }
}
