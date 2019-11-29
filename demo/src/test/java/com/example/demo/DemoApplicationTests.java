package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    @Test
    void test_db() {
        List<User> users = userMapper.list();
        for(User user : users){
            System.out.println(user.toString());
        }
    }

}
