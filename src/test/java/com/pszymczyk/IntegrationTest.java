package com.pszymczyk;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TripService tripService;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    TripReservationClient tripReservationClient;

    @Before
    public void setup() {
        tripReservationClient = new TripReservationClient(testRestTemplate);
    }

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripReservationClient.addTrip(tripCode);

        //and when book trip
        tripReservationClient.book(userId, tripCode);

        //then trip booked
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
