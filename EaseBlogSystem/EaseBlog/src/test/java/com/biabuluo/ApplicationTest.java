package com.biabuluo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 小宇
 * @date 2023-08-26:23:08
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */

@SpringBootTest
public class ApplicationTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encoderTest(){
        System.out.println(passwordEncoder.encode("1234"));
    }
}
