package com.example.smartHouse.controller;

import com.example.smartHouse.dto.AccommodationDto;
import com.example.smartHouse.dto.TakeDto;
import com.example.smartHouse.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AccommodationController.class)
class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService accommodationService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccommodationDto accommodationDto;
    private TakeDto takeDto;

    @BeforeEach
    void setUp() {
        // Sample DTO for the tests
        accommodationDto = new AccommodationDto();
        accommodationDto.setUserId(1L);
        accommodationDto.setTitle("Ocean View House");
        accommodationDto.setDescription("A beautiful house by the ocean.");
        accommodationDto.setLocation("Miami Beach, FL");
        accommodationDto.setPrice(200.0);
        accommodationDto.setMinGuest(2);
        accommodationDto.setMaxGuest(6);
        accommodationDto.setAvailable(List.of(LocalDate.now()));

        takeDto = new TakeDto();
        takeDto.setId(1L);
        takeDto.setDate(LocalDate.now());
    }

    @Test
    void testRegisterAccommodation_Success() throws Exception {
        mockMvc.perform(post("/api/accommodation/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accommodationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("success"));
    }

    @Test
    void testTakeAccommodation_Success() throws Exception {
        when(accommodationService.take(any(Long.class), any(LocalDate.class))).thenReturn(true);

        mockMvc.perform(post("/api/accommodation/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(takeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Accommodation taken successfully!"));
    }

    @Test
    void testTakeAccommodation_Error() throws Exception {
        when(accommodationService.take(any(Long.class), any(LocalDate.class))).thenReturn(false);

        mockMvc.perform(post("/api/accommodation/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(takeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ERROR"));
    }

    @Test
    void testRegisterAccommodation_InvalidInput() throws Exception {
        // Invalid input: no title
        accommodationDto.setTitle(null);

        mockMvc.perform(post("/api/accommodation/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accommodationDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testTakeAccommodation_NonExistentId() throws Exception {
        when(accommodationService.take(any(Long.class), any(LocalDate.class))).thenReturn(false);

        mockMvc.perform(post("/api/accommodation/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(takeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ERROR"));
    }

    @Test
    void testTakeAccommodation_DateNotAvailable() throws Exception {
        // Simulating that the date is already taken or not available
        when(accommodationService.take(1L, LocalDate.now())).thenReturn(false);

        mockMvc.perform(post("/api/accommodation/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(takeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ERROR"));
    }

}
