package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {

    List<User> list();

    List<User> findByUsername(String username);

    User getOne(String userId);

    int delete(String userId);
}
