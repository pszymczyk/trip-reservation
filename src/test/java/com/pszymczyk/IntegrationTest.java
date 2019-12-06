package com.pszymczyk;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class, IntegrationConfiguration.class })
@ActiveProfiles("integration")
class IntegrationTest {

    @Autowired
    TripReservationClient tripReservationClient;

    @Test
    void Should_book_trip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripReservationClient.addTrip(tripCode);

        //and when book trip
        URI reservationLocation = tripReservationClient.book(userId, tripCode);

        //then trip booked
        assertThat(tripReservationClient.findReservation(reservationLocation)).contains(userId);
    }
}
