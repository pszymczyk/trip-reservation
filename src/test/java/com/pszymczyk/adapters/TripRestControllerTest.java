package com.pszymczyk.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pszymczyk.application.TripService;

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
}