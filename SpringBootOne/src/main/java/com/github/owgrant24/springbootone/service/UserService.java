package com.github.owgrant24.springbootone.service;

import com.github.owgrant24.springbootone.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    void deleteUserById(int id);
}
