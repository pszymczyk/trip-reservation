package com.pszymczyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
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
