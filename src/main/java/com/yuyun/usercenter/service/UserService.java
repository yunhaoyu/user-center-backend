package com.yuyun.usercenter.service;

import com.yuyun.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyun.usercenter.model.domain.request.UserAddRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author yuyun
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param planetCode 星球编号
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     */
    int userLogout(HttpServletRequest request);

    /**
     * 添加新用户
     * @param addUser
     * @return
     */
    int addUser(UserAddRequest addUser);
}
