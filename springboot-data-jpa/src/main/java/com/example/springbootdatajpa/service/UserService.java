package com.example.springbootdatajpa.service;

import com.example.springbootdatajpa.entity.User;

import java.util.List;

public interface UserService {
    List<User> list();

    List<User> findByUsername(String username);

    User get(String userId);

    User findByUsernameAndPassword(String username, String password);
}
