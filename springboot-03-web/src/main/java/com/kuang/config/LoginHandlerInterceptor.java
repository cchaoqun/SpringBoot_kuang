package com.kuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-1:35
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功 有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        //没有登录
        if(loginUser==null){
            request.setAttribute("msg","Please Login First");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            return true;
        }


    }
}
