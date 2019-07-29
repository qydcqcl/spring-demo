package com.example.service;

import com.example.bean.User;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 5:33
 */
public interface UserServiceI {

    User findByName(String userName);

    void saveUser(User user);
}
