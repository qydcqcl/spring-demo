package com.example.dao.impl;

import com.example.bean.Student;
import com.example.dao.StudentDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:33
 */
@Repository
public class StudentDaoImpl implements StudentDaoI {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Student student) {
//        String sql = "insert into student(sno,sname,ssex) values(?,?,?)";
//        Object[] args = { student.getSno(), student.getName(), student.getSex() };
//        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
//        return jdbcTemplate.update(sql, args, argTypes);

        String sql = "insert into student(sno,sname,ssex) values(:sno,:name,:sex)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int update(Student student) {
        String sql = "update student set sname = ?,ssex = ? where sno = ?";
        Object[] args = { student.getName(), student.getSex(), student.getSno() };
        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteBysno(String sno) {
        String sql = "delete from student where sno = ?";
        Object[] args = { sno };
        int[] argTypes = { Types.VARCHAR };
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        String sql = "select * from student where sno = ?";
        Object[] args = { sno };
        int[] argTypes = { Types.VARCHAR };
        List<Student> studentList = jdbcTemplate.query(sql, args, argTypes, new StudentResult());
        if (studentList != null && studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> queryStudentsListMap() {
        String sql = "select * from student";
        return jdbcTemplate.queryForList(sql);
    }
}
