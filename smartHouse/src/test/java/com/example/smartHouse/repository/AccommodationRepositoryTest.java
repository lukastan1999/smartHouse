package com.example.smartHouse.repository;

import com.example.smartHouse.entity.Accommodation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // ovom anotacijom kofigurisemo privremenu bazu (h20)
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    private Accommodation accommodation;

    @BeforeEach
    void setUp() {
        accommodation = new Accommodation();
        accommodation.setTitle("Test Accommodation");
        accommodation.setPrice(200.0);
        accommodation.setAvailable(new ArrayList<>(List.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2))));
        accommodation.setTaken(new ArrayList<>());
    }

    @Test
    void testSaveAccommodation_Success() {
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        assertNotNull(savedAccommodation.getId());
        assertEquals("Test Accommodation", savedAccommodation.getTitle());
    }

    @Test
    void testFindById_Success() {
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(savedAccommodation.getId());

        assertTrue(foundAccommodation.isPresent());
        assertEquals(savedAccommodation.getId(), foundAccommodation.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(999L);
        assertFalse(foundAccommodation.isPresent());
    }

    @Test
    void testDeleteAccommodation_Success() {
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        accommodationRepository.delete(savedAccommodation);

        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(savedAccommodation.getId());
        assertFalse(foundAccommodation.isPresent());
    }

    @Test
    void testUpdateAccommodation_Success() {
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        savedAccommodation.setPrice(300.0); // Update the price

        Accommodation updatedAccommodation = accommodationRepository.save(savedAccommodation);
        assertEquals(300.0, updatedAccommodation.getPrice());
    }

//    @Test
//    @Sql("/test-sql/sample-data.sql")
//    void testFindAllWithSQLData() {
//        List<Accommodation> accommodations = accommodationRepository.findAll();
//        assertFalse(accommodations.isEmpty());
//        assertEquals(2, accommodations.size()); // Assuming 2 rows are inserted via SQL
//    }

}
