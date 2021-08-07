package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    User findUserByEmail(String email);
}
