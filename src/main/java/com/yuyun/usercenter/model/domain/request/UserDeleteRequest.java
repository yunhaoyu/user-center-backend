package com.yuyun.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDeleteRequest implements Serializable {

    private static final long serialVersionUID = -7959034090261204522L;

    private Long id;
}
