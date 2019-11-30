package com.example.springbootdatajpa.controller;

import com.example.springbootdatajpa.dto.R;
import com.example.springbootdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public R list(){
        try {
            return R.isOk().data(userService.list());
        }catch (Exception e){
            return R.isError(e);
        }
    }

    @GetMapping("get/{userId}")
    public R get(@PathVariable String userId){
        try {
            return R.isOk().data(userService.findByUsername(userId));
        }catch (Exception e){
            return R.isError(e);
        }
    }

    @GetMapping("get/{username}/{password}")
    public R get(@PathVariable("username") String username, @PathVariable("password") String password){
        try {
            return R.isOk().data(userService.findByUsernameAndPassword(username, password));
        }catch (Exception e){
            return R.isError(e);
        }
    }
}
