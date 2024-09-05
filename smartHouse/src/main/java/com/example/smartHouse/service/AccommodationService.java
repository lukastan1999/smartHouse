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
    private final UserService userService;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository, UserService userService) {
        this.accommodationRepository = accommodationRepository;
        this.userService = userService;
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
        // pripisivanje smestaja gazdi
        Boolean b = userService.addAccommodation(accommodationDto.getUserId(), accommodation.getId());
        if (b) {
            accommodationRepository.save(accommodation);
            return accommodation;
        }
        return null;
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

    public Boolean redefine(Long id, List<LocalDate> datumi) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isPresent()) {
            List<LocalDate> daniZauzeti = accommodation.get().getAvailable();
            for (LocalDate dejt : datumi) {
                for (LocalDate datum : daniZauzeti) {
                    if (datum.equals(dejt)) {
                        return false;
                    }
                }
            }
            accommodation.get().setAvailable(datumi);
            accommodationRepository.save(accommodation.get());
            return true;
        }
        return false;
    }

    public Accommodation get(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        return accommodation.orElse(null);
    }
}
