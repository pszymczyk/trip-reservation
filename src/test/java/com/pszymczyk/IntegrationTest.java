package com.pszymczyk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegrationTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    @Test
    void Should_book_trip() {
        //given
        var trip = new Trip("abc", 10);
        tripRepository.save(trip);

        //when
        tripService.book("kazik", "abc");

        //then
        assertThat(tripRepository.findTrip("abc").getReservations())
                .hasSize(1)
                .first()
                .matches(reservation -> reservation.id() != null)
                .matches(reservation -> reservation.status().equals(Reservation.ReservationStatus.NEW));
    }
}
