package com.example.basedao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author hzq
 * @date 2019/7/29 0029 下午 10:07
 */
public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
