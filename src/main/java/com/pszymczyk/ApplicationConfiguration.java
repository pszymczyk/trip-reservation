package com.pszymczyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApplicationConfiguration {

    @Bean
    TripService tripService(TripRepository tripRepository) {
        return new TripService(tripRepository);
    }

    @Bean
    TripRepository tripRepository() {
        return new InMemoryTripRepository();
    }
}
