package com.pszymczyk;

import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Profile("integration")
class TripReservationClient {

    private final TestRestTemplate testRestTemplate;

    public TripReservationClient(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    void book(String userId, String tripCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> bookRequestEntity = new HttpEntity<>("{\n"
                + "  \"userId\": \"" + userId + "\"\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/trips/" + tripCode + "/reservations", HttpMethod.POST, bookRequestEntity, Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }


    public void addTrip(Trip trip) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> bookRequestEntity = new HttpEntity<>("{\n"
                + "  \"tripCode\": \""+ trip.getTripCode() + "\",  \n"
                + "  \"seatsNumber\": 10\n"
                + "}", headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/trips", HttpMethod.POST, bookRequestEntity, Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
    }

    public String findTrip(String tripCode) {
        return testRestTemplate.getForEntity("/trips/search/findByTripCode?tripCode=" + tripCode, String.class).getBody();
    }
}
