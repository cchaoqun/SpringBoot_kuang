package com.kuang.swaggerdemo.controller;

import com.kuang.swaggerdemo.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-16:57
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //只要我们的接口中, 返回值中存在实体类, 他就会被扫描到swagger中
    @PostMapping("/user")
    public User user(){
        return new User();
    }

    //operation接口 不是放在类上
    @ApiOperation("hello控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username){
        return "hello "+username;
    }

    @ApiOperation("Post测试类")
    @PostMapping(value="/postt")
    public User postt(@ApiParam("用户名") User user){
        return user;
    }
}
