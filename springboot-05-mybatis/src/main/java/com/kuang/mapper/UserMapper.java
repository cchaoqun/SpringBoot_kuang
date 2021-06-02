package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-18:42
 */
@Mapper
@Repository
public interface UserMapper {

    //查
    List<User> queryUsers();
    //增
    int addUser(User user);
    //删
    int deleteUser(int id);
    //改
    int updateUser(User user);
}
