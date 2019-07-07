package com.pszymczyk.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pszymczyk.application.TripService;
import com.pszymczyk.domain.TripRepository;

@Configuration
class Config {

    @Bean
    TripService tripService(TripRepository tripRepository) {
        return new TripService(tripRepository);
    }

}
