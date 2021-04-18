package com.github.owgrant24.one.dao;

import com.github.owgrant24.one.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    void deleteUserById(int id);
}
