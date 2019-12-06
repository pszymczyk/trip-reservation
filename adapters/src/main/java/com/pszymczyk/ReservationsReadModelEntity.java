package com.pszymczyk;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class ReservationsReadModelEntity {

    @Id
    private String reservationId;

    private String value;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
