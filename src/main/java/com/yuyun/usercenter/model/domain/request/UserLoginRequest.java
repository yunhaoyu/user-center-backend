package com.yuyun.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author yuyun
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -7959034090261204522L;

    private String userAccount;

    private String userPassword;

}
