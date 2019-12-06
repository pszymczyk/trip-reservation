package com.pszymczyk.integration;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pszymczyk.integration.TripReservationClient;

@Configuration
@Profile("integration")
class IntegrationConfiguration {

    @Bean
    TripReservationClient tripReservationClient(TestRestTemplate testRestTemplate) {
        return new TripReservationClient(testRestTemplate);
    }
}
