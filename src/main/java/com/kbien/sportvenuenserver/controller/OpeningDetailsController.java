package com.kbien.sportvenuenserver.controller;

import com.kbien.sportvenuenserver.csv.CsvReader;
import com.kbien.sportvenuenserver.entity.OpeningDetails;
import com.kbien.sportvenuenserver.entity.Venue;
import com.kbien.sportvenuenserver.service.OpeningDetailsService;
import com.kbien.sportvenuenserver.service.VenueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class OpeningDetailsController {
    private final OpeningDetailsService openingDetailsService;
    private final VenueService venueService;
    private final CsvReader csvReader;

    @PostMapping(path = "/opening-details/{venueId}")
    public ResponseEntity<Venue> saveOpeningDetails(@PathVariable("venueId") Long venueId, @RequestParam String text) {
        List<OpeningDetails> openingDetails = csvReader.readCsvFormat(text).stream().map(openingDetailsService::saveOpeningDetails).collect(Collectors.toList());
        Venue venue = venueService.getVenue(venueId);
        venue.setOpeningDetails(openingDetails);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/venue/save").toUriString());
        return ResponseEntity.created(uri).body(venueService.saveVenue(venue));
    }
}
