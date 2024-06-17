package com.itheima.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test01(){
        String pwd = "123456";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);
    }
    @Test
    public void test02(){
        String pwd = "123456";
        String enpwd = "$2a$10$6pkLmRWR36nYj9pfmiwly.1WmRbqsZgjcuDEDj5O7sDQ92L2TPo.W";
        boolean matches = passwordEncoder.matches(pwd, enpwd);
        System.out.println(matches);
    }

}
