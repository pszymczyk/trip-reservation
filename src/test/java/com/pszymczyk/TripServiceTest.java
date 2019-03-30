package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripService tripService;
    private TripDao testTripDao;

    @Before
    public void setup() {
        testTripDao = mock(TripDao.class);
        tripService = new TripService(testTripDao);
    }

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        Trip trip = new Trip(1);
        when(testTripDao.findTrip(anyString())).thenReturn(trip);

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(1);
    }

    @Test
    public void shouldBookTripEvenWhenThereIsALotNotConfirmedReservations() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        List<Reservation> reservations = new ArrayList<>();
        Reservation newReservation = new Reservation(userId, tripCode);
        reservations.add(newReservation);
        Reservation canceledReservation = new Reservation(userId, tripCode).cancel();
        reservations.add(canceledReservation);
        Trip trip = new Trip(1, reservations);
        when(testTripDao.findTrip(anyString())).thenReturn(trip);

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(3);
    }
    
    @Test
    public void shouldThrowExceptionWhenTryToBookFullReservedTrip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation(userId, tripCode).confirm();
        reservations.add(reservation);
        Trip trip = new Trip(1, reservations);
        when(testTripDao.findTrip(anyString())).thenReturn(trip);

        //when
        Throwable thrown = catchThrowable(() -> tripService.book(userId, tripCode));

        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }
}