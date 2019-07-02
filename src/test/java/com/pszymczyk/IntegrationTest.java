package com.pszymczyk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripReservationClient tripReservationClient;

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";
        tripRepository.save(new Trip(tripCode, 10));

        //when
        tripReservationClient.book(userId, tripCode);

        //then
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }

}
