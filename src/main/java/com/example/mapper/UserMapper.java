package com.example.mapper;

import com.example.basedao.IBaseDao;
import com.example.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author hzq
 * @date 2019/7/29 0029 下午 10:20
 */
@Mapper
@Component
public interface UserMapper extends IBaseDao<User> {
}
