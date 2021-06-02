package com.kuang;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-21:27
 */

//AOP:拦截器
@EnableWebSecurity
public class SecuirtyConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有可以访问, 功能也只有对应权限的人才可以访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限会到登录页面, 需要开启登录的页面
        //  /login
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        //放置网站攻击: get post
        http.csrf().disable();//关闭csrf功能

        //注销 开启了注销功能
        http.logout().deleteCookies("remove").invalidateHttpSession(true)
                .logoutSuccessUrl("/");//注销成功跳到首页

        //开启记住我功能 cookies
        http.rememberMe().rememberMeParameter("remember me");
    }

    //认证
    //密码编码: passwordEncoder
    //spring security 5.0+ 新增了很多的加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("ccq").password(new BCryptPasswordEncoder().encode("123")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");
    }
}
