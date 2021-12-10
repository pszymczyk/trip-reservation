package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceTest {

    private TripService tripService;
    private TripRepository tripRepository;

    @BeforeEach
    void setup() {
        tripRepository = mock(TripRepository.class);
        tripService = new TripService(tripRepository);
    }

    @Test
    void Should_book_trip() {
        //given
        var userId = "some-id";
        var tripCode = "some-trip";
        var trip = new Trip(tripCode, 1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(1);
    }

    @Test
    void Should_book_trip_even_when_there_is_a_lot_not_confirmed_reservations() {
        //given
        var userId = "some-id";
        var tripCode = "some-trip";
        var reservations = new ArrayList<Reservation>();
        var newReservation = new Reservation(userId);
        reservations.add(newReservation);
        var canceledReservation = new Reservation(userId).cancel();
        reservations.add(canceledReservation);
        var trip = new Trip(tripCode, 1, reservations);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(3);
    }

    @Test
    void Should_throw_exception_when_cannot_find_trip() {
        //when
        var thrown = catchThrowable(() -> tripService.book("some-id", "some-trip"));

        //then
        assertThat(thrown).isInstanceOf(TripNotFound.class);
    }
    
    @Test
    void Should_throw_exception_when_try_to_book_full_reserved_trip() {
        //given
        var userId = "some-id";
        var tripCode = "some-trip";
        var reservations = new ArrayList<Reservation>();
        var reservation = new Reservation(userId).confirm();
        reservations.add(reservation);
        var trip = new Trip(tripCode, 1, reservations);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        var thrown = catchThrowable(() -> tripService.book(userId, tripCode));

        //then
        assertThat(thrown).isInstanceOf(TripFullyBooked.class);
    }
}
