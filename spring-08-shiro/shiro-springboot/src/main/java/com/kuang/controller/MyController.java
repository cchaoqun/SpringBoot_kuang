package com.kuang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/29-22:49
 */

@Controller
public class MyController {


    @RequestMapping({"/", "/index"})
    public String toIndex(Model model){
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取了当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //执行登录方法
        try {
            subject.login(token);


            return "index";
        } catch (UnknownAccountException e) {//同户名不存在
            model.addAttribute("msg", "同户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {//密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "Unauthorized";
    }
}
