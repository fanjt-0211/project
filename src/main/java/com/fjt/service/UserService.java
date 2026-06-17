package com.fjt.service;

import com.fjt.pojo.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    void add(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
}