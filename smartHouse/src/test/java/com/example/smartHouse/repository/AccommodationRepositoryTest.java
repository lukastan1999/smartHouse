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

@DataJpaTest // This annotation sets up an in-memory database and configures Spring Data JPA
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    private Accommodation accommodation;

    @BeforeEach
    void setUp() {
        // Set up an Accommodation entity to be used in test cases
        accommodation = new Accommodation();
        accommodation.setTitle("Test Accommodation");
        accommodation.setPrice(200.0);
        accommodation.setAvailable(new ArrayList<>(List.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2))));
        accommodation.setTaken(new ArrayList<>());
    }

    @Test
    void testSaveAccommodation_Success() {
        // Save accommodation and verify the result
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        assertNotNull(savedAccommodation.getId());
        assertEquals("Test Accommodation", savedAccommodation.getTitle());
    }

    @Test
    void testFindById_Success() {
        // Save accommodation first, then find it by ID
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(savedAccommodation.getId());

        assertTrue(foundAccommodation.isPresent());
        assertEquals(savedAccommodation.getId(), foundAccommodation.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        // Attempt to find an accommodation with a non-existing ID
        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(999L);
        assertFalse(foundAccommodation.isPresent());
    }

    @Test
    void testDeleteAccommodation_Success() {
        // Save an accommodation first, then delete it and verify it's deleted
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        accommodationRepository.delete(savedAccommodation);

        Optional<Accommodation> foundAccommodation = accommodationRepository.findById(savedAccommodation.getId());
        assertFalse(foundAccommodation.isPresent());
    }

    @Test
    void testUpdateAccommodation_Success() {
        // Save accommodation first, update it, and verify the update
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        savedAccommodation.setPrice(300.0); // Update the price

        Accommodation updatedAccommodation = accommodationRepository.save(savedAccommodation);
        assertEquals(300.0, updatedAccommodation.getPrice());
    }

//    @Test
//    @Sql("/test-sql/sample-data.sql") // Load data from an external SQL file
//    void testFindAllWithSQLData() {
//        // Verify that the SQL data is loaded and retrievable
//        List<Accommodation> accommodations = accommodationRepository.findAll();
//        assertFalse(accommodations.isEmpty());
//        assertEquals(2, accommodations.size()); // Assuming 2 rows are inserted via SQL
//    }
}
