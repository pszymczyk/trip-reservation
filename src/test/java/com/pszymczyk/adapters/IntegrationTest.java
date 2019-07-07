package com.pszymczyk.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pszymczyk.domain.Trip;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TripReservationClient tripReservationClient;

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";
        tripReservationClient.addTrip(new Trip(tripCode, 10));

        //when
        tripReservationClient.book(userId, tripCode);

        //then
        assertThat(tripReservationClient.findTrip(tripCode)).contains("kazik");
    }

}
