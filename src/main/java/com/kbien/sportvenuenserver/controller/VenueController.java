package com.kbien.sportvenuenserver.controller;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.service.UserService;
import com.kbien.sportvenuenserver.service.VenueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

@Data
class VenueToUserForm {
    private Long userId;
    private Long venueId;
}
