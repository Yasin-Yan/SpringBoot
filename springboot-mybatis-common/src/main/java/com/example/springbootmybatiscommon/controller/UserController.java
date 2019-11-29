package com.example.springbootmybatiscommon.controller;

import com.example.springbootmybatiscommon.entity.User;
import com.example.springbootmybatiscommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public List<User> list(User user){
        return userService.list(user);
    }

    @RequestMapping("get/{userId}")
    public User get(@PathVariable("userId") String userId){
        User user = new User();
        user.setUserId(userId);
        return userService.get(user);
    }

    @RequestMapping("update")
    public int update(User user){
        return userService.update(user);
    }

    @RequestMapping("delete")
    public int delete(User user){
        return userService.delete(user);
    }

    @RequestMapping("save")
    public int save(User user){
        return userService.save(user);
    }
}
