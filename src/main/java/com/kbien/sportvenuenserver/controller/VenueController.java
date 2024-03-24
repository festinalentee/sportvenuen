package com.kbien.sportvenuenserver.controller;

import com.kbien.sportvenuenserver.dto.BookingDto;
import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Booking;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.service.UserService;
import com.kbien.sportvenuenserver.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class VenueController {
    private final UserService userService;
    private final VenueService venueService;

    @PostMapping(path = "/venue")
    public ResponseEntity<Venue> saveVenue(@RequestBody Venue venue) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = userService.getUser(email);
        Venue savedVenue = venueService.saveVenue(venue, account);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/venue/save").toUriString());
        return ResponseEntity.created(uri).body(savedVenue);
    }

    @GetMapping("/venue/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(venueService.getVenue(id));
    }

    @PutMapping("/venue")
    public ResponseEntity<Venue> updateVenue(@RequestBody Venue venue) {
        return ResponseEntity.accepted().body(venueService.updateVenue(venue));
    }

    @PostMapping(path = "/venue/booking")
    public ResponseEntity<Booking> bookVenue(@RequestBody BookingDto bookingDto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = userService.getUser(email);
        Booking savedBooking = venueService.bookVenue(bookingDto, account);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/venue/booking").toUriString());
        return ResponseEntity.created(uri).body(savedBooking);
    }

    @PostMapping(path = "favourites/{userId}/{venueId}")
    public void addToFavourites(@PathVariable("userId") Long userId, @PathVariable("venueId") Long venueId) {
        venueService.addToFavourites(userId, venueId);
    }

    @PutMapping(path = "favourites/{userId}/{venueId}")
    public void removeFromFavourites(@PathVariable("userId") Long userId, @PathVariable("venueId") Long venueId) {
        venueService.removeFromFavourites(userId, venueId);
    }

    @GetMapping("/venue/search")
    public ResponseEntity<List<Venue>> searchVenues(@RequestParam String venueType, @RequestParam String city) {
        return ResponseEntity.ok(venueService.searchVenues(venueType, city));
    }
}