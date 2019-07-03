package com.pszymczyk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.pszymczyk.Reservation.ReservationStatus.CONFIRMED;
import static com.pszymczyk.Reservation.ReservationStatus.NEW;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

public class TripEntityTest {

    @Test
    public void shouldApplyReservationStatusChange() {
        //given
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(UUID.randomUUID());
        reservationEntity.setStatus("NEW");
        TripEntity entity = new TripEntity();
        entity.setReservations(singleton(reservationEntity));

        Reservation reservation = new Reservation(reservationEntity.getId(), "kazik", CONFIRMED);
        Trip trip = new Trip("123", 10, singletonList(reservation));

        //when
        entity.apply(trip);

        //then
        Assertions.assertThat(entity.getReservations()
                         .stream()
                         .findFirst()
                         .map(e -> e.getStatus())
                         .get())
            .isEqualTo("CONFIRMED");
    }

    // @Test
    // public void shouldApplyReservationStatusChangeOnEveryRecord() {
    //     //given
    //     ReservationEntity oneReservationEntity = new ReservationEntity();
    //     oneReservationEntity.setId(UUID.randomUUID());
    //     oneReservationEntity.setStatus("NEW");
    //
    //     ReservationEntity anotherReservationEntity = new ReservationEntity();
    //     anotherReservationEntity.setId(UUID.randomUUID());
    //     anotherReservationEntity.setStatus("NEW");
    //
    //     TripEntity entity = new TripEntity();
    //     entity.setReservations(new HashSet<>(asList(oneReservationEntity, anotherReservationEntity));
    //
    //     Reservation reservation = new Reservation(anotherReservationEntity.getId(), "kazik", CONFIRMED);
    //     Trip trip = new Trip("123", 10, singletonList(reservation));
    //
    //     //when
    //     entity.apply(trip);
    //
    //     //then
    //     Assertions.assertThat(entity.getReservations()
    //                                 .stream()
    //                                 .findFirst()
    //                                 .map(e -> e.getStatus())
    //                                 .get())
    //               .isEqualTo("CONFIRMED");
    // }

    @Test
    public void shouldApplyNewReservation() {
        //given
        TripEntity entity = new TripEntity();
        entity.setReservations(new HashSet<>());

        Reservation reservation = new Reservation(UUID.randomUUID(), "kazik", NEW);
        Trip trip = new Trip("123", 10, singletonList(reservation));

        //when
        entity.apply(trip);

        //then
        Assertions.assertThat(entity.getReservations()
                                    .stream()
                                    .findFirst()
                                    .map(e -> e.getStatus())
                                    .get())
                  .isEqualTo("NEW");
    }
}