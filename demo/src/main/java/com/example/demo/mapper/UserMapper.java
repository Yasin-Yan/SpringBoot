package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

//    查询所有用户信息
    @Select("SELECT * FROM t_user WHERE 1 = 1")
    List<User> list();

//    通过姓名查询用户信息
    @Select("SELECT * FROM t_user WHERE username like #{username}")
    List<User> findByUsername(String username);

//    通过Id查询用户信息
    @Select("SELECT * FROM t_user WHERE user_id like #{userId}")
    User getOne(String userId);

//    通过Id删除用户
    @Delete("DELETE FROM t_user WHERE user_id like #{userId}")
    int delete(String userId);

}
