package com.example.service.impl;

import com.example.bean.User;
import com.example.mapper.UserMapper;
import com.example.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 5:33
 */
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", userName);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() != 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        user.setCreateTime(LocalDateTime.now().toString());
        userMapper.insert(user);
    }
}
