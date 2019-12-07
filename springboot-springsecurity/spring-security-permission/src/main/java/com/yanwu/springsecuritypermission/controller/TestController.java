package com.yanwu.springsecuritypermission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security!";
    }

    @GetMapping("/index")
    public Object index(Authentication authentication){
        return authentication;
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')") //拥有admin权限的人才能访问
    public String authenticationTest(){
        return "你拥有root权限，可以查看";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String isAdmin(){
        return "你拥有root权限，可以查看";
    }
}
