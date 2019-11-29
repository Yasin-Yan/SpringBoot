package com.example.springbootannotation.mapper;

import com.example.springbootannotation.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 介绍Mybatis注解的常见使用方式
 */
public interface UserMapper {
    /**
     * 方式一: 使用注解编写SQL
     */
    @Select("SELECT * FROM t_user")
    @Results({
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "mobileNum", column = "PHONE_NUM")
    })
    List<User> list();

    /**
     * 方式2: 使用注解指定某个工具类的方法来动态编写SQL
     */
    @SelectProvider(type = UserSqlProvider.class, method = "listByUsername")
    List<User> listByUsername(String username);

    /**
     * 延伸：上述两种方式都可以附加@Results注解来指定结果集的映射关系
     * 如果符合下划线转驼峰的匹配项可以直接省略不写
     */
    @Results({
            @Result(property = "mobileNum", column = "PHONE_NUM")
    })
    @Select("SELECT * FROM t_user")
    List<User> listSample();

    /**
     * 延伸：无论什么方式，如果涉及多个参数，则必须加上@Param注解，否则无法使用EL表达式获取参数
     */
    @Results({
            @Result(property = "mobileNum", column = "PHONE_NUM")
    })
    @Select("SELECT * FROM t_user WHERE username like #{username} and password like #{password}")
    User getOne(@Param("username") String username, @Param("password") String password);

    @Results({
            @Result(property = "mobileNum", column = "PHONE_NUM")
    })
    @SelectProvider(type = UserSqlProvider.class, method = "getBadUser")
    User getBadUser(@Param("username") String username, @Param("password") String password);

}
