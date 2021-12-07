package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.OpeningDetails;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.repository.OpeningDetailsRepository;
import com.kbien.sportvenuenserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

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
    public OpeningDetails getOpeningDetails(Long venueId) {
        String localDate = String.valueOf(LocalDate.now());
        log.info("Fetching openingDetails for {}", localDate);
        Venue venue = venueRepository.findVenueById(venueId);
        return venue.getOpeningDetails().stream().filter(openingDetails ->
                (LocalDate.parse(openingDetails.getDateFrom()).isBefore(LocalDate.now())
                        || LocalDate.parse(openingDetails.getDateFrom()).isEqual(LocalDate.now()))
                        && (LocalDate.parse(openingDetails.getDateTo()).isAfter(LocalDate.now())
                        || LocalDate.parse(openingDetails.getDateTo()).isEqual(LocalDate.now()))).findFirst().orElse(null);
    }
}
