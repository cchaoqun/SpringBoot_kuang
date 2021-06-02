package com.kuang.serviece;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-22:01
 */

//zookeeper: 服务注册与发现
@DubboService //可以被扫描到, 在项目启动后就自动注册到注册中心
@Component//使用了Dubbo后尽量不用service注解
public class TicketServiceImpl implements TicketService{

    @Override
    public String getTicket() {
        return "CCQ's Java";
    }
}
