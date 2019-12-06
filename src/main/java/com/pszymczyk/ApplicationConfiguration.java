package com.pszymczyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
class ApplicationConfiguration {

    @Bean
    TripService tripService(TripRepository tripRepository, ReservationsReadModel reservationsReadModel) {
        return new TripService(tripRepository, reservationsReadModel);
    }
}
