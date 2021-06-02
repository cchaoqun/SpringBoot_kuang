package com.kuang.helloSpring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/27-0:52
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        //调用业务, 接收前端参数
        return "hello,world";
    }
}
