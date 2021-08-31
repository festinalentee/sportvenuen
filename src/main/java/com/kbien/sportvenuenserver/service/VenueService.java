package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Venue;

public interface VenueService {
    Venue saveVenue(Venue venue);

    void addVenueToUser(Long userId, Long venueId);
}
