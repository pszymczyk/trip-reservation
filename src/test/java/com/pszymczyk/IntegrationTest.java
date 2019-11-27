package com.pszymczyk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    TripService tripService;

    @Autowired
    TripRepository tripRepository;

    @Test
    void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";
        tripRepository.save(new Trip(tripCode, 10));

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }
}
