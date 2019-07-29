package com.example.service.impl;

import com.example.bean.Student;
import com.example.dao.StudentDaoI;
import com.example.mapper.StudentMapper;
import com.example.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:19
 */
@Service
public class StudentServiceImpl implements StudentServiceI {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentDaoI studentDao;

    @Override
    public int add(Student student) {
//        return studentMapper.add(student);
        return studentDao.add(student);
    }

    @Override
    public Student update(Student student) {
        studentMapper.update(student);
        return studentMapper.queryStudentBySno(student.getSno());
    }

    @Override
    public int deleteBysno(String sno) {
        return studentMapper.deleteBysno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return studentMapper.queryStudentBySno(sno);
    }

    @Override
    public List<Map<String, Object>> queryStudentsListMap() {
        return studentDao.queryStudentsListMap();
    }
}
