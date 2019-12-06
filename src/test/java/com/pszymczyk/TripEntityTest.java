package com.pszymczyk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.pszymczyk.ReservationEntity;
import com.pszymczyk.TripEntity;
import com.pszymczyk.Reservation;
import com.pszymczyk.Trip;

import static com.pszymczyk.Reservation.ReservationStatus.CONFIRMED;
import static com.pszymczyk.Reservation.ReservationStatus.NEW;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class TripEntityTest {

    @Test
    void Should_apply_reservation_status_change() {
        //given
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(UUID.randomUUID());
        reservationEntity.setStatus("NEW");
        TripEntity entity = new TripEntity();
        entity.setReservations(singleton(reservationEntity));

        Reservation reservation = new Reservation(reservationEntity.getId(), "kazik", CONFIRMED);
        Trip trip = new Trip("123", 10, singletonList(reservation));

        //when
        entity.update(trip);

        //then
        assertThat(entity.getReservations()
                         .stream()
                         .findFirst()
                         .map(e -> e.getStatus())
                         .get())
                .isEqualTo("CONFIRMED");
    }

    @Test
    void Should_apply_reservation_status_change_on_every_record() {
        //given
        ReservationEntity oneReservationEntity = new ReservationEntity();
        oneReservationEntity.setId(UUID.randomUUID());
        oneReservationEntity.setStatus("NEW");

        ReservationEntity anotherReservationEntity = new ReservationEntity();
        anotherReservationEntity.setId(UUID.randomUUID());
        anotherReservationEntity.setStatus("NEW");

        TripEntity entity = new TripEntity();
        entity.setReservations(new HashSet<>(asList(oneReservationEntity, anotherReservationEntity)));

        Reservation reservation = new Reservation(oneReservationEntity.getId(), "kazik", CONFIRMED);
        Reservation anotherReservation = new Reservation(anotherReservationEntity.getId(), "kazik", CONFIRMED);
        Trip trip = new Trip("123", 10, Arrays.asList(reservation, anotherReservation));

        //when
        entity.update(trip);

        //then
        assertThat(entity.getReservations()
                         .stream()
                         .filter(e -> e.getStatus() == CONFIRMED.name())
                         .count()).isEqualTo(2);
    }

    @Test
    void Should_apply_new_reservation() {
        //given
        TripEntity entity = new TripEntity();
        entity.setReservations(new HashSet<>());

        Reservation reservation = new Reservation(UUID.randomUUID(), "kazik", NEW);
        Trip trip = new Trip("123", 10, singletonList(reservation));

        //when
        entity.update(trip);

        //then
        assertThat(entity.getReservations()
                         .stream()
                         .findFirst()
                         .map(ReservationEntity::getStatus)
                         .get())
                .isEqualTo("NEW");
    }
}
