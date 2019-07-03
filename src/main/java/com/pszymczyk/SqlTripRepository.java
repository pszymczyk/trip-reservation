package com.pszymczyk;

import java.util.List;

import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
class SqlTripRepository implements TripRepository {

    private final CrudTripRepository crudTripRepository;

    SqlTripRepository(CrudTripRepository crudTripRepository) {
        this.crudTripRepository = crudTripRepository;
    }

    @Override
    public Trip findTrip(String tripCode) {
        TripEntity tripEntity = crudTripRepository.findByTripCode(tripCode);
        List<Reservation> reservations = tripEntity.getReservations()
                .stream()
                .map(entity -> new Reservation(entity.getId(), entity.getUserId(), Reservation.ReservationStatus.valueOf(entity.getStatus())))
                .collect(toList());

        return new Trip(tripEntity.getTripCode(), tripEntity.getSeatsNumber(), reservations);
    }

    @Override
    public void save(Trip trip) {
        TripEntity tripEntity = crudTripRepository.findByTripCode(trip.getTripCode());
        tripEntity.apply(trip);
    }
}
