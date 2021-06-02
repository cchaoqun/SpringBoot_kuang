package com.kuang.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/27-22:28
 */

//如果想diy定制一些功能, 只要写这个组件, 然后将它交给springboot springboot就会帮我们自动装配
//扩展 springmvc  dispatcherServlet
//如果我们要扩展springmvc 官方建议我们这样去做
@Configuration
//@EnableWebMvc  //导入一个类(DelegatingWebMvcConfiguration.class)  从容器中获取所有的webmvcconfig
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }


    //配置拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/user/login", "/", "/css/**", "/js/**", "/img/**");
    }
}
