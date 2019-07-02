package com.pszymczyk;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
class SpringDataTripRepository implements TripRepository {

    private final CrudTripsRepository crudTripsRepository;

    public SpringDataTripRepository(CrudTripsRepository crudTripsRepository) {
        this.crudTripsRepository = crudTripsRepository;
    }

    @Override
    public Trip findTrip(String tripCode) {
        TripEntity tripEntity = crudTripsRepository.findByTripCode(tripCode);

        List<Reservation> reservations = tripEntity.getReservations()
                                                   .stream()
                                                   .map(reservationEntity -> new Reservation(
                                                           reservationEntity.getId(),
                                                           reservationEntity.getUserId(),
                                                           Reservation.ReservationStatus.valueOf(reservationEntity.getStatus())))
                                                   .collect(Collectors.toList());

        return new Trip(tripEntity.getTripCode(),
                tripEntity.getSeatsNumber(), reservations);

    }

    @Override
    public void save(Trip trip) {
        TripEntity tripEntity = crudTripsRepository.findByTripCode(trip.getTripCode());
        tripEntity.update(trip);
        crudTripsRepository.save(tripEntity);
    }
}
