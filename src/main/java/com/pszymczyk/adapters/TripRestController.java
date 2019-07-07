package com.pszymczyk.adapters;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pszymczyk.application.TripService;

@RestController
class TripRestController {

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
    void bookTrip(@PathVariable String tripCode, @RequestBody Request request) {
        tripService.book(request.userId, tripCode);
    }
}
