package com.pszymczyk.integration;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.pszymczyk.application.Application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class, IntegrationConfiguration.class })
@ActiveProfiles("integration")
class IntegrationTest {

    @Autowired
    TripReservationClient tripReservationClient;

    @Test
    void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripReservationClient.addTrip(tripCode);

        //and when book trip
        URI reservationLocation = tripReservationClient.book(userId, tripCode);

        //then trip booked
        await().untilAsserted(() -> assertThat(tripReservationClient.findReservation(reservationLocation)).contains("kazik"));
    }
}
