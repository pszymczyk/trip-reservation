package com.pszymczyk;

import org.springframework.stereotype.Component;

@Component
class SqlReservationsReadModel implements ReservationsReadModel {

    private final CrudReservationsReadModelEntityRepository crudReservationsReadModelEntityRepository;

    public SqlReservationsReadModel(CrudReservationsReadModelEntityRepository crudReservationsReadModelEntityRepository) {
        this.crudReservationsReadModelEntityRepository = crudReservationsReadModelEntityRepository;
    }

    @Override
    public void update(ReservationAdded reservationAdded) {
        ReservationsReadModelEntity reservationsReadModelEntity = new ReservationsReadModelEntity();
        reservationsReadModelEntity.setReservationId(reservationAdded.getReservationId());
        reservationsReadModelEntity.setValue("{\n"
                + "  \"reservation\": \"Reservation in status " + reservationAdded.getStatus() + " for trip " + reservationAdded.getTripCode() + " and user " + reservationAdded.getUserId() + "\"\n"
                + "}");
        crudReservationsReadModelEntityRepository.save(reservationsReadModelEntity);
    }
}
