package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 11:23
 */
@Mapper
public interface OracleStudentMapper {

    List<Map<String, Object>> getAllStudents();
}
