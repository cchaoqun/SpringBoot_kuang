package com.kuang.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-19:12
 */

@Service
public class AsyncService {

    //告诉spring这个是一个异步的方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理");
    }

}
