package com.example.service;

import com.example.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 5:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceI userService;

    @Test
    public void test() {
        User user = userService.findByName("scott");
        Assert.assertEquals("用户名为scott", "scott", user.getUserName());
    }
}
