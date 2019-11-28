package com.pszymczyk.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.pszymczyk.application.Application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    TripReservationClient tripReservationClient;

    @BeforeEach
    void setup() {
        tripReservationClient = new TripReservationClient(testRestTemplate);
    }

    @Test
    void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripReservationClient.addTrip(tripCode);

        //and when book trip
        tripReservationClient.book(userId, tripCode);

        //then trip booked
        await().untilAsserted(() -> assertThat(tripReservationClient.findTrip(tripCode)).contains("kazik"));
    }

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
