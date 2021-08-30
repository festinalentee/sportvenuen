package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Role;

import java.util.List;

public interface UserService {
    Account getUserById(Long id);

    Account saveUser(Account account);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);

    Account getUser(String email);

    Role getRole(String name);

    List<Account> getUsers();

    Account updateUser(Account account);
}
