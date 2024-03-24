package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findBookingById(Long id);
}
