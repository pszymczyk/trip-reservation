package com.pszymczyk;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldBookTrip() {
        //given trip
        String tripCode = "123";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("{\n"
                + "  \"tripCode\": \"" + tripCode + "\",\n"
                + "  \"seatsNumber\": 10\n"
                + "}", headers);

        ResponseEntity<?> response = testRestTemplate.exchange("/trips", HttpMethod.POST, entity, Void.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);

        //when
        HttpEntity<String> entity1 = new HttpEntity<>("{\n"
                + "  \"userId\": \"Kazik\"\n"
                + "}", headers);

        ResponseEntity<?> bookResponse = testRestTemplate.exchange("/trips/" + tripCode + "/reservations", HttpMethod.POST, entity1, Void.class);

        assertThat(bookResponse.getStatusCodeValue()).isEqualTo(200);

        //then
        ResponseEntity<String> tripResponse = testRestTemplate.getForEntity("/trips/search/findByTripCode?tripCode=" + tripCode, String.class);
        Assertions.assertThat(tripResponse.getBody())
                  .contains("\"userId\" : \"Kazik\"")
                  .contains("\"status\" : \"NEW\"");
    }
}
