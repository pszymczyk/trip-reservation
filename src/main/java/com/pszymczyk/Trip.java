package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;

class Trip {

    private String tripCode;
    private int seats;
    private List<Reservation> reservations = new ArrayList<>();

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}