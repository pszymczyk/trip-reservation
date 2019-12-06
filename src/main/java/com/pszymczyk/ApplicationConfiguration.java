package com.pszymczyk;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
class ApplicationConfiguration {

    @Bean
    TripService tripService(TripRepository tripRepository, EventPublisher eventPublisher) {
        return new TripService(tripRepository, eventPublisher) {

            @Override
            @Transactional
            public ReservationSummary book(String userId, String tripCode) {
                return super.book(userId, tripCode);
            }
        };
    }

    @Bean
    EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
