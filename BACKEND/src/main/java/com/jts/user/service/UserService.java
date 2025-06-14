package com.jts.user.service;

import com.jts.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAll();
    void deleteById(Integer id);
    boolean existsById(Integer id);
}