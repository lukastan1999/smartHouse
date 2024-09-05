package com.example.smartHouse.controller;

import com.example.smartHouse.dto.RedefineDto;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(AccommodationController.class)
public class RedefineAccommodationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationService accommodationService;

    @Autowired
    private ObjectMapper objectMapper;

    private RedefineDto redefineDto;

    @BeforeEach
    void setUp() {
        // Sample DTO for the tests
        redefineDto = new RedefineDto();
        redefineDto.setId(1L);
        redefineDto.setDatumi(List.of(LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    @Test
    void testRedefineAccommodation_Success() throws Exception {
        // Simulate the service returning true for successful redefinition
        when(accommodationService.redefine(any(Long.class), any(List.class))).thenReturn(true);

        mockMvc.perform(post("/api/accommodation/redefine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(redefineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Accommodation redefined successfully!"));
    }

    @Test
    void testRedefineAccommodation_InvalidId() throws Exception {
        // Simulate the service returning false for non-existent accommodation ID
        when(accommodationService.redefine(any(Long.class), any(List.class))).thenReturn(false);

        mockMvc.perform(post("/api/accommodation/redefine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(redefineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ERROR"));
    }

//    @Test
//    public void testRedefineAccommodation_EmptyDateList() throws Exception {
//        RedefineDto redefineDto = new RedefineDto();
//        redefineDto.setId(1L);
//        redefineDto.setDatumi(Collections.emptyList()); // Empty list
//
//        mockMvc.perform(post("/api/accommodation/redefine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(redefineDto)))
//                .andExpect(status().isBadRequest())  // Expecting 400 status
//                .andExpect(jsonPath("$.message").value("Validation Error"));
//    }
//
//    @Test
//    public void testRedefineAccommodation_MissingId() throws Exception {
//        RedefineDto redefineDto = new RedefineDto();
//        redefineDto.setId(null); // Null ID
//        redefineDto.setDatumi(Arrays.asList(LocalDate.of(2024, 9, 6), LocalDate.of(2024, 9, 7)));
//
//        mockMvc.perform(post("/api/accommodation/redefine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(redefineDto)))
//                .andExpect(status().isBadRequest())  // Expecting 400 status
//                .andExpect(jsonPath("$.message").value("Validation Error"));
//    }



    @Test
    void testRedefineAccommodation_DateAlreadyTaken() throws Exception {
        // Simulate the service returning false for dates already taken
        when(accommodationService.redefine(any(Long.class), any(List.class))).thenReturn(false);

        mockMvc.perform(post("/api/accommodation/redefine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(redefineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ERROR"));  // Expecting an ERROR message
    }

    @Test
    void testRedefineAccommodation_InvalidDateFormat() throws Exception {
        // Simulate invalid date format in the request body
        String invalidJson = "{\"id\":1, \"datumi\":[\"invalid-date\"]}";

        mockMvc.perform(post("/api/accommodation/redefine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());  // Expecting 400 Bad Request for invalid date format
    }

    @Test
    void testRedefineAccommodation_SingleDateInsteadOfList() throws Exception {
        String invalidJson = "{\"id\":1, \"datumi\":\"2024-09-05\"}"; // Wrong type for "datumi"

        mockMvc.perform(post("/api/accommodation/redefine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());  // Expecting 400 Bad Request for incorrect data type
    }


}
