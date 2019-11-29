package com.example.springbootannotation.mapper;

import com.example.springbootannotation.pojo.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * 主要用途：根据复杂的业务需求来动态生成SQL
 * 目标：使用Java工具类来替代传统的XML文件
 */
public class UserSqlProvider {
    /**
     * 方式1：在工具类的方法里，可以自己手工编写SQL
     */
    public String listByUsername(String username){
        return "SELECT * FROM t_user WHERE username = #{username}";
    }

    /**
     * 方式2：根据官方提供的API来编写动态SQL
     */
    public String getBadUser(@Param("username") String username, @Param("password") String password){
        return new SQL(){{
            SELECT("*");
            FROM("t_user");
            if(username != null && password != null){
                WHERE("username like #{username} and password like #{password}");
            }else{
                WHERE("1=2");
            }
        }}.toString();
    }

    /**
     * 1.用于获取结果集的映射关系
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    /**
     * 2.打印结果集的映射关系
     */
    public static void main(String[] args) {
        System.out.println(getResultsStr(User.class));
    }
}
