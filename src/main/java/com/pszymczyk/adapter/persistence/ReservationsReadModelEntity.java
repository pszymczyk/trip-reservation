package com.pszymczyk.adapter.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class ReservationsReadModelEntity {

    @Id
    private int entityId;

    private String tripCode;

    private String value;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
