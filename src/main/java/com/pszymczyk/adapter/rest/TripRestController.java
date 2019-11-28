package com.pszymczyk.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pszymczyk.application.TripService;
import com.pszymczyk.domain.model.TripFullyBooked;
import com.pszymczyk.domain.model.TripNotFound;

@RestController
class TripRestController {

    private static final Logger log = LoggerFactory.getLogger(TripRestController.class);

    static class Request {
        String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    private final TripService tripService;

    public TripRestController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping(path = "/trips/{tripCode}/reservations", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    void bookTrip(@PathVariable String tripCode, @RequestBody Request request) {
        tripService.book(request.userId, tripCode);
    }

    @ExceptionHandler(value = { TripNotFound.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    void handleTripNotFound(TripNotFound tripNotFound) {
        log.debug(tripNotFound.getMessage());
    }

    @ExceptionHandler(value = { TripFullyBooked.class })
    @ResponseStatus(value = HttpStatus.CONFLICT)
    void handleTripFullyBooked(TripFullyBooked tripFullyBooked) {
        log.debug(tripFullyBooked.getMessage());
    }
}
