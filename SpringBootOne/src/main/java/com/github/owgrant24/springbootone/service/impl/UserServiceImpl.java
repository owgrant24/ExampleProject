package com.github.owgrant24.springbootone.service.impl;

import com.github.owgrant24.springbootone.model.Role;
import com.github.owgrant24.springbootone.model.User;
import com.github.owgrant24.springbootone.repository.RoleRepository;
import com.github.owgrant24.springbootone.repository.UserRepository;
import com.github.owgrant24.springbootone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(3));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
