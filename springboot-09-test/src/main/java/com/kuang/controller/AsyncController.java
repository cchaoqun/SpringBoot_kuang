package com.kuang.controller;

import com.kuang.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-19:13
 */

@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){
        //停止三秒
        asyncService.hello();
        return "ok";

    }
}
