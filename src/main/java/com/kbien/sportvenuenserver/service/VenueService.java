package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Venue;

public interface VenueService {
    Venue saveVenue(Venue venue);

    void addVenueToUser(Long userId, Long venueId);

    Venue getVenue(Long id);

    Venue updateVenue(Venue venue);

    void addToFavourites(Long userId, Long venueId);

    void removeFromFavourites(Long userId, Long venueId);
}
