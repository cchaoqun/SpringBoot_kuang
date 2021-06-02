package com.kuang.swaggerdemo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-17:59
 */
//@Api(注释)
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
