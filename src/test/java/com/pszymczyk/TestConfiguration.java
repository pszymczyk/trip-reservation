package com.pszymczyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class TestConfiguration {

    // @Bean
    // @Primary
    TripRepository tripRepository() {
        return new InMemoryTripRepository();
    }

}
