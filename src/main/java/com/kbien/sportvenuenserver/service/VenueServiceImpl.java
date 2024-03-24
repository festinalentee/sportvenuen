package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.dto.BookingDto;
import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Booking;
import com.kbien.sportvenuenserver.entity.OpeningHours;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.repository.BookingRepository;
import com.kbien.sportvenuenserver.repository.UserRepository;
import com.kbien.sportvenuenserver.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Venue saveVenue(Venue venue, Account account) {
        log.info("Saving new venue {} to the database", venue.getVenueName());
        venue.setAccount(account);
        for (OpeningHours openingHours : venue.getOpeningHours()) {
            openingHours.setVenue(venue);
        }
        return venueRepository.save(venue);
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
    public Booking bookVenue(BookingDto bookingDto, Account account) {
        log.info("Saving new booking to the database");
        DayOfWeek dayOfWeek = bookingDto.getDate().getDayOfWeek();
        Integer durationOfBooking = bookingDto.getTimeTo() - bookingDto.getTimeFrom();
        Double pricePerHour = venueRepository.getPriceByVenueIdAndDayOfWeek(bookingDto.getVenueId(), dayOfWeek);
        bookingDto.setPrice(durationOfBooking * pricePerHour);
        Venue venue = venueRepository.getById(bookingDto.getVenueId());
        Booking booking = new Booking(null, bookingDto.getDate(), bookingDto.getTimeFrom(), bookingDto.getTimeTo(), bookingDto.getPrice(), account, venue);
        return bookingRepository.save(booking);
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
