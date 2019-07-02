package com.pszymczyk;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
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
        testRestTemplate.exchange("/trips/" + tripCode + "/reservations", HttpMethod.POST, bookRequestEntity, Void.class);
    }
}
