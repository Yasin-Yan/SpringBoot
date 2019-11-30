package com.example.springbootdatajpa.repository;

import com.example.springbootdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 接口方法的名称，符合约定则无需实现即可访问
     *
     * 1.查询方法以find|read|get开头，条件属性首字母需大写
     * 2.如果方法的最后一个参数是 Sort 或者 Pageable 类型，也会提取相关的信息，以便按规则进行排序或者分页查询
     * 3.从右往左截取第一个大写字母开头的字符串
     */
    //@Query("select u from User u where u.name = ?1")
    List<User> findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
