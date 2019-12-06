package com.pszymczyk;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
class ApplicationConfiguration {

    @Bean
    TripService tripService(TripRepository tripRepository, EventPublisher eventPublisher) {
        return new TripService(tripRepository, eventPublisher);
    }

    @Bean
    EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
