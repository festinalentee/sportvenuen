package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.OpeningHours;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.repository.UserRepository;
import com.kbien.sportvenuenserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;

    @Override
    public Venue saveVenue(Venue venue) {
        log.info("Saving new venue {} to the database", venue.getVenueName());
        for (OpeningHours openingHours : venue.getOpeningHours()) {
            openingHours.setVenue(venue);
        }
        return venueRepository.save(venue);
    }

    @Override
    public void addVenueToUser(Long userId, Long venueId) {
        log.info("Adding venue {} to user {}", venueId, userId);
        Account account = userRepository.findUserById(userId);
        Venue venue = venueRepository.findVenueById(venueId);
        account.getVenues().add(venue);
    }

    @Override
    public Venue getVenue(Long id) {
        log.info("Fetching venue {}", id);
        return venueRepository.findVenueById(id);
    }

    @Override
    public Venue updateVenue(Venue venue) {
        log.info("Updating venue {}", venue.getVenueName());
        return venueRepository.save(venue);
    }

    @Override
    public void addToFavourites(Long userId, Long venueId) {
        log.info("Adding venue {} to favourites user {}", venueId, userId);
        Venue venue = venueRepository.findVenueById(venueId);
        Account account = userRepository.findUserById(userId);
        account.getFavourites().add(venue);
    }

    @Override
    public void removeFromFavourites(Long userId, Long venueId) {
        log.info("Removing venue {} from favourites user {}", venueId, userId);
        Venue venue = venueRepository.findVenueById(venueId);
        Account account = userRepository.findUserById(userId);
        account.getFavourites().remove(venue);
    }

    @Override
    public List<Venue> searchVenues(String venueType, String city) {
        return venueRepository.findVenuesByVenueTypeContainingAndCityContaining(venueType, city);
    }
}
