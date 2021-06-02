package com.kuang.config;

import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/29-22:57
 */

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前用户的对象
        Subject subject = SecurityUtils.getSubject();
        //拿到User对象
        User current = (User) subject.getPrincipal();
        //设置当前用户的权限
        info.addStringPermission(current.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");
        //账户认证
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){
            //用户不存在
            return null; //UnknowAccountException
        }
        Subject curSubject = SecurityUtils.getSubject();
        Session session = curSubject.getSession();
        session.setAttribute("loginUSer",user);
        //密码认证 shiro来做
        return new SimpleAuthenticationInfo(user,user.getPwd(), "");
    }
}
