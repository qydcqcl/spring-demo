package com.example.dao;

import com.example.bean.Student;

import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:31
 */
public interface StudentDaoI {

    int add(Student student);

    int update(Student student);

    int deleteBysno(String sno);

    Student queryStudentBySno(String sno);

    List<Map<String, Object>> queryStudentsListMap();
}
