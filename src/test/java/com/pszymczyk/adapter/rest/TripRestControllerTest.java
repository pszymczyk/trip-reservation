package com.pszymczyk.adapter.rest;

import org.hamcrest.core.StringEndsWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.pszymczyk.application.TripService;
import com.pszymczyk.domain.model.ReservationSummary;
import com.pszymczyk.domain.model.TripFullyBooked;
import com.pszymczyk.domain.model.TripNotFound;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = TripRestController.class)
@WebMvcTest
class TripRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService service;

    @Test
    void shouldHandleBookTripRequest() throws Exception {
        String reservationId = "999";
        when(service.book(anyString(), anyString())).thenReturn(new ReservationSummary(reservationId, "NEW"));

        mockMvc.perform(
                post("/trips/234/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n"
                                + "  \"userId\": \"kazik\"\n"
                                + "}"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(header().string(HttpHeaders.LOCATION, new StringEndsWith(true, reservationId)));
    }

    @Test
    void shouldHandleTripNotFound() throws Exception {
        doThrow(new TripNotFound("234")).when(service).book(anyString(), anyString());

        mockMvc.perform(
                post("/trips/234/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n"
                                + "  \"userId\": \"kazik\"\n"
                                + "}"))
               .andExpect(status().isNotFound());
    }

    @Test
    void shouldHandleTripFullyBooked() throws Exception {
        doThrow(new TripFullyBooked("234")).when(service).book(anyString(), anyString());

        mockMvc.perform(
                post("/trips/234/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n"
                                + "  \"userId\": \"kazik\"\n"
                                + "}"))
               .andExpect(status().isConflict());
    }
}
