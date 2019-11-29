package com.example.springbootmybatiscommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.springbootmybatiscommon.mapper")
public class SpringbootMybatisCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisCommonApplication.class, args);
    }

}
