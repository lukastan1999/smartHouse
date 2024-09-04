package com.example.smartHouse.service;

import com.example.smartHouse.dto.AccommodationDto;
import com.example.smartHouse.entity.Accommodation;
import com.example.smartHouse.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public Accommodation registerAccommodation(AccommodationDto accommodationDto) {
        Accommodation accommodation = new Accommodation();
        accommodation.setTitle(accommodationDto.getTitle());
        accommodation.setDescription(accommodationDto.getDescription());
        accommodation.setLocation(accommodationDto.getLocation());
        accommodation.setPrice(accommodationDto.getPrice());
        accommodation.setMinGuest(accommodationDto.getMinGuest());
        accommodation.setMaxGuest(accommodationDto.getMaxGuest());
        accommodation.setAmenities(accommodationDto.getAmenities());
        accommodation.setAvailable(accommodationDto.getAvailable());
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    public Boolean take(Long id, LocalDate date) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isPresent()) {
            List<LocalDate> dani = accommodation.get().getAvailable();
            List<LocalDate> daniZauzeti = accommodation.get().getTaken();
            for (LocalDate dejt : dani) {
                if (dejt.equals(date)) {
                    dani.remove(dejt);
                    daniZauzeti.add(dejt);
                    accommodation.get().setAvailable(dani);
                    accommodation.get().setTaken(daniZauzeti);
                    accommodationRepository.save(accommodation.get());
                    return true;
                }
            }
        }
        return false;
    }
}
