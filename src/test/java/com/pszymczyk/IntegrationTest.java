package com.pszymczyk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegrationTest {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripService tripService;

    @Test
    void Should_book_trip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripRepository.save(new Trip(tripCode, 10));

        //and when book trip
        tripService.book(userId, tripCode);

        //then trip booked
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }
}
