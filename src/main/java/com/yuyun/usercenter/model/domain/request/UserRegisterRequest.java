package com.yuyun.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author yuyun
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -7959034090261204522L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String planetCode;
}
