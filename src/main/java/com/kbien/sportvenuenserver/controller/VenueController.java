package com.kbien.sportvenuenserver.controller;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.service.UserService;
import com.kbien.sportvenuenserver.service.VenueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}

@Data
class VenueToUserForm {
    private Long userId;
    private Long venueId;
}
