package com.kbien.sportvenuenserver.repository;

import com.kbien.sportvenuenserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findUserById(Long id);

    Account findUserByEmail(String email);
}
