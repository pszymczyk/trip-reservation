package com.pszymczyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TripController {

    static class BookTripRequest {
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping(path = "/trips/{tripCode}/reservations", consumes = "application/json")
    void book(@PathVariable String tripCode, @RequestBody BookTripRequest request) {
        tripService.book(request.userId, tripCode);
    }

}
