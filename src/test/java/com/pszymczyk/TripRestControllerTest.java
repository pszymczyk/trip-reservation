package com.pszymczyk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TripRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService service;

    @Test
    public void shouldHandleBookTripRequest() throws Exception {
        mockMvc.perform(
                post("/trips/234/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n"
                                + "  \"userId\": \"kazik\"\n"
                                + "}"))
               .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldHandleTripNotFound() throws Exception {
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
    public void shouldHandleTripFullyBooked() throws Exception {
        Mockito.doThrow(new TripFullyBooked("234")).when(service).book(anyString(), anyString());

        mockMvc.perform(
                post("/trips/234/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n"
                                + "  \"userId\": \"kazik\"\n"
                                + "}"))
               .andExpect(status().isConflict());
    }
}
