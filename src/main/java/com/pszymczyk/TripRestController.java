package com.pszymczyk;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
class TripRestController {

    private static final Logger log = LoggerFactory.getLogger(TripRestController.class);

    static class Request {
        String tripCode;
        String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTripCode() {
            return tripCode;
        }

        public void setTripCode(String tripCode) {
            this.tripCode = tripCode;
        }
    }

    private final TripService tripService;

    public TripRestController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping(path = "/reservations", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> bookTrip(@RequestBody Request request) {
        ReservationSummary reservationSummary = tripService.book(request.userId, request.tripCode);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(reservationSummary.getReservationId()).toUri();
        return ResponseEntity.created(location).build();
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
