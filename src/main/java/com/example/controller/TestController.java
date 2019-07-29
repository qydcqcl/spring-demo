package com.example.controller;

import com.example.annotation.Log;
import com.example.bean.Student;
import com.example.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:20
 */
@RestController
public class TestController {

    @Autowired
    private StudentServiceI studentService;

    @RequestMapping(value = "/queryStudent", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return studentService.queryStudentBySno(sno);
    }

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student student) {
        studentService.add(student);
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public int saveStudent(String sno,String name,String sex) {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);
        return this.studentService.add(student);
    }

    @RequestMapping(value = "deletestudent", method = RequestMethod.GET)
    public int deleteStudentBySno(String sno) {
        return this.studentService.deleteBysno(sno);
    }

    @RequestMapping(value = "/queryallstudent")
    public List<Map<String, Object>> queryAllStudent() {
        return studentService.queryStudentsListMap();
    }

    @Log("执行方法一")
    @GetMapping("/one")
    public void methodOne(String name) { }

    @Log("执行方法二")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("执行方法三")
    @GetMapping("/three")
    public void methodThree(String name, String age) { }
}
