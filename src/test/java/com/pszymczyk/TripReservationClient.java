package com.pszymczyk;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;

class TripReservationClient {

    private final TestRestTemplate testRestTemplate;

    TripReservationClient(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    void book(String userId, String tripCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("{\n"
                + "  \"userId\": \"" + userId + "\"\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/trips/" + tripCode + "/reservations", POST, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(CREATED);
    }


    public void addTrip(String tripCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("{\n"
                + "  \"tripCode\": \""+ tripCode + "\",  \n"
                + "  \"seatsNumber\": 10\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/trips", POST, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(CREATED);
    }

    public String findTrip(String tripCode) {
        return testRestTemplate.getForEntity("/reservations/search/findByTripCode?tripCode=" + tripCode, String.class).getBody();
    }
}
