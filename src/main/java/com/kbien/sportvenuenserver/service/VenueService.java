package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.dto.BookingDto;
import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Booking;
import com.kbien.sportvenuenserver.entity.Venue;

import java.util.List;

public interface VenueService {
    Venue saveVenue(Venue venue, Account account);

    Venue getVenue(Long id);

    Venue updateVenue(Venue venue);

    Booking bookVenue(BookingDto bookingDto, Account account);

    void addToFavourites(Long userId, Long venueId);

    void removeFromFavourites(Long userId, Long venueId);

    List<Venue> searchVenues(String venueType, String city);
}
