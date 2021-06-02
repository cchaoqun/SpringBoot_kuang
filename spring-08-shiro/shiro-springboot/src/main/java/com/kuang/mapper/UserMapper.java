package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-14:35
 */

@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

}
