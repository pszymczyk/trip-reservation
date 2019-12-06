package com.pszymczyk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void Should_book_trip() {
        //given
        String userId = "kazik";
        String tripCode = "123";

        //when add trip
        tripRepository.save(new Trip(tripCode, 10));

        //and when book trip
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("{\n"
                + "  \"tripCode\": \"" + tripCode + "\",\n"
                + "  \"userId\": \"" + userId + "\"\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/reservations", POST, entity, Void.class);

        //then trip booked
        assertThat(response.getStatusCode()).isEqualByComparingTo(CREATED);
        assertThat(tripRepository.findTrip(tripCode).getReservations()).hasSize(1);
    }
}
