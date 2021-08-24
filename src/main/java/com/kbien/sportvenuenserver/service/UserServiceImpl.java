package com.kbien.sportvenuenserver.service;


import com.kbien.sportvenuenserver.entity.Role;
import com.kbien.sportvenuenserver.entity.User;
import com.kbien.sportvenuenserver.repository.RoleRepository;
import com.kbien.sportvenuenserver.repository.UserDetailsRepository;
import com.kbien.sportvenuenserver.repository.UserRepository;
import com.kbien.sportvenuenserver.request.RegisterRequest;
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

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("Username not found in the database");
        } else {
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public com.kbien.sportvenuenserver.entity.UserDetails getUserDetailsById(Long id) {
        return userDetailsRepository.findUserDetailsById(id);
    }

    @Override
    public User saveUser(RegisterRequest registerRequest) {
        log.info("Saving new user {} to the database", registerRequest.getUser().getEmail());
        registerRequest.getUser().setPassword(passwordEncoder.encode(registerRequest.getUser().getPassword()));
        var user = userRepository.save(registerRequest.getUser());
        var details = registerRequest.getUserDetails();
        details.setId(user.getId());
        userDetailsRepository.save(details);
        return user;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        User user = userRepository.findUserByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
