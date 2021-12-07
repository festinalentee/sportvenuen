package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.OpeningDetails;

public interface OpeningDetailsService {
    OpeningDetails saveOpeningDetails(OpeningDetails openingDetails);

    OpeningDetails getOpeningDetails(Long venueId);
}
