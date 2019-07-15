package com.pszymczyk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Test
    public void shouldBookTrip() {
        //given
        String userId = "kazik";
        String tripCode = "123";
        tripRepository.save(new Trip(tripCode, 10));

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> bookRequestEntity = new HttpEntity<>("{\n"
                + "  \"userId\": \"" + userId + "\"\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/trips/" + tripCode + "/reservations", HttpMethod.POST, bookRequestEntity, Void.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }
}
