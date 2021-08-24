package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserDetailsById(Long id);
}
