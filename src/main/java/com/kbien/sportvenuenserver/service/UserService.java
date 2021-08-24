package com.kbien.sportvenuenserver.service;

import com.kbien.sportvenuenserver.entity.Role;
import com.kbien.sportvenuenserver.entity.User;
import com.kbien.sportvenuenserver.entity.UserDetails;
import com.kbien.sportvenuenserver.request.RegisterRequest;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    UserDetails getUserDetailsById(Long id);
    User saveUser(RegisterRequest registerRequest);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
}
