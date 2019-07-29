package com.example.dao.impl;

import com.example.bean.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:44
 */
public class StudentResult implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setSno(rs.getString("sno"));
        student.setName(rs.getString("sname"));
        student.setSex(rs.getString("ssex"));
        return student;
    }
}
