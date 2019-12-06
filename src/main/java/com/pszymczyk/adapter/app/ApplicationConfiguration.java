package com.pszymczyk.adapter.app;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.pszymczyk.application.TripService;
import com.pszymczyk.domain.model.EventPublisher;
import com.pszymczyk.domain.model.TripRepository;

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
