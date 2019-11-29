package com.example.springbootmybatiscommon.mapper;

import com.example.springbootmybatiscommon.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 继承通用Mapper获取CURD方法
 */
public interface UserMapper extends Mapper<User> {
}
