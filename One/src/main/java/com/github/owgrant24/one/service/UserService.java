package com.github.owgrant24.one.service;

import com.github.owgrant24.one.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    void deleteUserById(int id);
}
