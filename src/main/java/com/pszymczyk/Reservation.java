package com.pszymczyk;

import java.util.UUID;

public class Reservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED

    }

    private UUID id;
    private String userId;
    private String tripCode;
    private ReservationStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
