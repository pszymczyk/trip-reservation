package com.pszymczyk;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;

@EnableAsync
@Configuration
class Config {

    @Bean
    TripService tripService(TripRepository tripRepository, EventPublisher eventPublisher) {
        return new TripService(tripRepository, eventPublisher) {

            @Override
            @Transactional
            public void book(String userId, String tripCode) {
                super.book(userId, tripCode);
            }
        };
    }

    @Bean
    EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return applicationEventPublisher::publishEvent;
    }
}
