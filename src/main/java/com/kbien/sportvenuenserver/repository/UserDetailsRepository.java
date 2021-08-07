package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.User;
import com.kbien.sportvenuenserver.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findUserDetailsById(Long id);
}
