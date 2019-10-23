package com.pszymczyk;

public class ReservationAdded implements DomainEvent {

    private final String tripCode;
    private final String reservationId;
    private final String userId;
    private final String status;

    public ReservationAdded(String tripCode, String reservationId, String userId, String status) {
        this.tripCode = tripCode;
        this.reservationId = reservationId;
        this.userId = userId;
        this.status = status;
    }

    public String getTripCode() {
        return tripCode;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }
}
