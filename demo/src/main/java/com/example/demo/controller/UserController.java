package com.example.demo.controller;

import com.example.demo.dto.R;
import com.example.demo.service.UserService;
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
            return R.isFail(e);
        }
    }

    @GetMapping("get/{userId}")
    public R getOne(@PathVariable("userId") String userId){
        try {
            return R.isOk().data(userService.getOne(userId));
        }catch (Exception e){
            return R.isFail(e);
        }
    }

    @GetMapping("del/{userId}")
    public R delete(@PathVariable("userId") String userId){
        try {
            return R.isOk().data(userService.delete(userId));
        }catch (Exception e){
            return R.isFail(e);
        }
    }

}
