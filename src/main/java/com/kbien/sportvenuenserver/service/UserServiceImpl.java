package com.kbien.sportvenuenserver.service;


import com.kbien.sportvenuenserver.entity.Account;
import com.kbien.sportvenuenserver.entity.Role;
import com.kbien.sportvenuenserver.repository.RoleRepository;
import com.kbien.sportvenuenserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findUserByEmail(email);
        if (account == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
    }

    @Override
    public Account getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Account saveUser(Account account) {
        log.info("Saving new user {} to the database", account.getEmail());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userRepository.save(account);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        Account account = userRepository.findUserByEmail(email);
        Role role = roleRepository.findByName(roleName);
        account.getRoles().add(role);
    }

    @Override
    public Account getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Role getRole(String name) {
        log.info("Fetching user role {}", name);
        return roleRepository.findByName(name);
    }

    @Override
    public List<Account> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public Account updateUser(Account account) {
        log.info("Updating user {}", account.getEmail());
        Account oldAccount = userRepository.findUserByEmail(account.getEmail());
        account.setId(oldAccount.getId());
        account.setPassword(oldAccount.getPassword());
        account.setRoles(oldAccount.getRoles());
        return userRepository.save(account);
    }
}
