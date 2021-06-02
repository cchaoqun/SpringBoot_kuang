package com.kuang.controller;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-18:53
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUsers")
    public List<User> queryUsers(){
        List<User> users = userMapper.queryUsers();
        for(User user:users){
            System.out.println(user);
        }
        return users;
    }

    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(10, "name1", "pwd1"));
        return "addUser-ok";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userMapper.deleteUser(id);
        return "deleteUser-ok";
    }

    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(10, "name1111", "pwd1111"));
        return "updateUser-ok";
    }

}
