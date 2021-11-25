package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.OpeningDetails;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.repository.OpeningDetailsRepository;
import com.kbien.sportvenuenserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OpeningDetailsServiceImpl implements OpeningDetailsService {
    private final VenueRepository venueRepository;
    private final OpeningDetailsRepository openingDetailsRepository;

    @Override
    public OpeningDetails saveOpeningDetails(OpeningDetails openingDetails) {
        log.info("Saving opening details {} to the database", openingDetails.getId());
        return openingDetailsRepository.save(openingDetails);
    }

    @Override
    public void addOpeningDetailsToVenue(Long venueId, Long openingDetailsId) {
        log.info("Adding opening details {} to venue {}", openingDetailsId, venueId);
        Venue venue = venueRepository.findVenueById(venueId);
        OpeningDetails openingDetails = openingDetailsRepository.findOpeningDetailsById(openingDetailsId);
        venue.getOpeningDetails().add(openingDetails);
    }
}
