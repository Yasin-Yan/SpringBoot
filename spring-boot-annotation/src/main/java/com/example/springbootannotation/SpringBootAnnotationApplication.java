package com.example.springbootannotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootannotation.mapper")
public class SpringBootAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAnnotationApplication.class, args);
    }

}
