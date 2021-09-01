package com.kbien.sportvenuenserver.controller;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.service.UserService;
import com.kbien.sportvenuenserver.service.VenueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/venue/save").toUriString());
        return ResponseEntity.created(uri).body(venueService.saveVenue(venue));
    }

    @PostMapping("/venue/add-to-user")
    public ResponseEntity<Account> addVenueToUser(@RequestBody VenueToUserForm form) {
        venueService.addVenueToUser(form.getUserId(), form.getVenueId());
        return ResponseEntity.ok().body(userService.getUserById(form.getUserId()));
    }

    @GetMapping("/venue/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(venueService.getVenue(id));
    }

    @PutMapping("/venue")
    public ResponseEntity<Venue> updateVenue(@RequestBody Venue venue) {
        return ResponseEntity.accepted().body(venueService.updateVenue(venue));
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

@Data
class VenueToUserForm {
    private Long userId;
    private Long venueId;
}
