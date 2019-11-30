package com.example.springbootdatajpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.springbootdatajpa.repository")
public class SpringbootDataJpaApplication {

//    解决JPA因为懒加载导致转换错误的问题
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataJpaApplication.class, args);
    }

}
