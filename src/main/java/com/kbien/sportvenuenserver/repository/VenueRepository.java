package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findVenueById(Long id);

    List<Venue> findVenuesByVenueTypeContainingAndCityContaining(String venueType, String city);

}
