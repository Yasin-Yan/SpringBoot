package com.yanwu.springbootswagger2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("首页管理")
@RestController
public class HelloController {

    @ApiOperation("欢迎界面")
    @GetMapping("/")
    public String hehe(){
        return "redirect:/swagger-ui.html";
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public String hello(){
        return "测试成功!";
    }
}
