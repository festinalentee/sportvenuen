package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.repository.UserRepository;
import com.kbien.sportvenuenserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
