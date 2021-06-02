package com.kuang.service;

import com.kuang.pojo.User;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-14:39
 */

public interface UserService {

    public User queryUserByName(String name);
}
