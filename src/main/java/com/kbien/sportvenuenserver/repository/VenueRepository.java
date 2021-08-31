package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findVenueById(Long id);
}
