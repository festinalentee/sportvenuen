package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findVenueById(Long id);

    List<Venue> findVenuesByVenueTypeContainingAndCityContaining(String venueType, String city);

    @Query("SELECT oh.price FROM Venue v JOIN v.openingHours oh WHERE v.id = :venueId AND oh.dayOfWeek = :dayOfWeek")
    Double getPriceByVenueIdAndDayOfWeek(@Param("venueId") Long venueId, @Param("dayOfWeek") DayOfWeek dayOfWeek);

}
