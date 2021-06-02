package com.kuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/29-22:56
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean            3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        /*
        添加shiro的内置过滤器
            anon 无需认证可以访问
            authc:  认证可以访问
            user:   必须拥有 记住我 功能才能用
            perms:  拥有对某个资源的权限才能访问
            role:   拥有某个角色权限才能访问
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权 (user用户有add权限才能访问) 正常的情况下会跳转到未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        //未授权的页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }
    //DefaultWebSecurityManager         2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建Real对象, 需要自定义类          1
    @Bean(name="userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect: 用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
