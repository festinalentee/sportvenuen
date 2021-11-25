package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.OpeningDetails;
import com.kbien.sportvenuenserver.entity.Venue;

public interface OpeningDetailsService {
    OpeningDetails saveOpeningDetails(OpeningDetails openingDetails);

    void addOpeningDetailsToVenue(Long venueId, Long openingDetailsId);
}
