package com.pszymczyk.application;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pszymczyk.application.TripService;
import com.pszymczyk.domain.Reservation;
import com.pszymczyk.domain.Trip;
import com.pszymczyk.domain.TripRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripService tripService;
    private TripRepository tripRepository;

    @Before
    public void setup() {
        tripRepository = mock(TripRepository.class);
        tripService = new TripService(tripRepository);
    }

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        Trip trip = new Trip(tripCode, 1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

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
        Reservation newReservation = new Reservation(userId);
        reservations.add(newReservation);
        Reservation canceledReservation = new Reservation(userId).cancel();
        reservations.add(canceledReservation);
        Trip trip = new Trip(tripCode, 1, reservations);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

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
        Reservation reservation = new Reservation(userId).confirm();
        reservations.add(reservation);
        Trip trip = new Trip(tripCode, 1, reservations);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        Throwable thrown = catchThrowable(() -> tripService.book(userId, tripCode));

        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }
}