package com.yunie.usercenter.service;

import com.yunie.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
* 用户服务测试
* @author yunie
*/

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("yu");
        user.setUserAccount("123");
        user.setUserPassword("123");
        user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Mha7DtRW5c02gs66mdeNIhp4bp2pW0yjEuAdU3lrJPTq3BK8SjbqtdAPa1HcygibDNjeRAUKPF2eqVfPGnNwtIw/132");
        user.setGender(0);
        user.setPhone("111");
        user.setEmail("222");

             boolean result=userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }



    @Test
    void userRegister() {

        String userAccount = "yunie";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yuy";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yunie";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yu nie";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        checkPassword= "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount="yunie";
        checkPassword="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount="yunie";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result>0);

    }
}