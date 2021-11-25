package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.OpeningDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningDetailsRepository extends JpaRepository<OpeningDetails, Long> {
    OpeningDetails findOpeningDetailsById(Long id);
}
