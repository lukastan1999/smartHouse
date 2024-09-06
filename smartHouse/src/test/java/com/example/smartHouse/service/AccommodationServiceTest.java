package com.example.smartHouse.service;

import com.example.smartHouse.dto.AccommodationDto;
import com.example.smartHouse.entity.Accommodation;
import com.example.smartHouse.repository.AccommodationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccommodationServiceTest {

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccommodationService accommodationService;

    private Accommodation accommodation;
    private AccommodationDto accommodationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setTitle("Test Accommodation");
        accommodation.setPrice(150.0);
        accommodation.setAvailable(new ArrayList<>(List.of(LocalDate.now())));
        accommodation.setTaken(new ArrayList<>(List.of(LocalDate.now().plusDays(3))));

        // AccommodationDto
        accommodationDto = new AccommodationDto();
        accommodationDto.setUserId(1L);
        accommodationDto.setTitle("Test Accommodation");
        accommodationDto.setPrice(150.0);
        accommodationDto.setAvailable(List.of(LocalDate.now()));
    }

    @Test
    void testRegisterAccommodation_Success() {
        when(userService.addAccommodation(any(Long.class), any(Long.class))).thenReturn(true);
        when(accommodationRepository.save(any(Accommodation.class))).thenReturn(accommodation);

        Accommodation result = accommodationService.registerAccommodation(accommodationDto);

        assertNotNull(result);
        assertEquals("Test Accommodation", result.getTitle());
        verify(accommodationRepository, times(1)).save(any(Accommodation.class));
    }

    @Test
    void testRegisterAccommodation_Failure() {
        when(userService.addAccommodation(any(Long.class), any(Long.class))).thenReturn(false);

        Accommodation result = accommodationService.registerAccommodation(accommodationDto);

        assertNull(result);
        verify(accommodationRepository, never()).save(any(Accommodation.class));
    }

    @Test
    void testTakeAccommodation_Success() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.of(accommodation));

        Boolean result = accommodationService.take(1L, LocalDate.now());

        assertTrue(result);
        assertFalse(accommodation.getAvailable().contains(LocalDate.now()));
        verify(accommodationRepository, times(1)).save(accommodation);
    }

    @Test
    void testTakeAccommodation_DateNotAvailable() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.of(accommodation));

        Boolean result = accommodationService.take(1L, LocalDate.of(2022, 1, 1));

        assertFalse(result);
        verify(accommodationRepository, never()).save(any(Accommodation.class));
    }

    @Test
    void testTakeAccommodation_NotFound() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Boolean result = accommodationService.take(1L, LocalDate.now());

        assertFalse(result);
        verify(accommodationRepository, never()).save(any(Accommodation.class));
    }

    @Test
    void testRedefineAccommodation_Success() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.of(accommodation));

        List<LocalDate> newDates = List.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        Boolean result = accommodationService.redefine(1L, newDates);

        assertTrue(result);
        assertEquals(newDates, accommodation.getAvailable());
        verify(accommodationRepository, times(1)).save(accommodation);
    }

    @Test
    void testRedefineAccommodation_DatesConflict() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.of(accommodation));

        // Try to redefine with conflicting dates (dates already taken)
        List<LocalDate> conflictingDates = List.of(LocalDate.now().plusDays(3));
        Boolean result = accommodationService.redefine(1L, conflictingDates);

        assertFalse(result);
        verify(accommodationRepository, never()).save(any(Accommodation.class));
    }

    @Test
    void testRedefineAccommodation_NotFound() {
        when(accommodationRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        List<LocalDate> newDates = List.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
        Boolean result = accommodationService.redefine(1L, newDates);

        assertFalse(result);
        verify(accommodationRepository, never()).save(any(Accommodation.class));
    }
}
