package com.yuyun.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void jiamiTest() {

        String encryptPassword = DigestUtils.md5DigestAsHex(("yuyun" + 12345678).getBytes());
        System.out.println(encryptPassword);

    }

}
