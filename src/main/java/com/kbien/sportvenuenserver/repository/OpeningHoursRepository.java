package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
    OpeningHours findOpeningHoursById(Long id);
}
