package com.example.service;

import com.example.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author hzq
 * @date 2019/7/28 0028 上午 10:17
 */
@CacheConfig(cacheNames = "student")
public interface StudentServiceI {

    int add(Student student);

    /**
     * CachePut将修改后的结果保存到chche sno作为redis的key值
     * @param student
     * @return
     */
    @CachePut(key = "#p0.sno")
    Student update(Student student);

    @CacheEvict(key = "#p0", allEntries = true)
    int deleteBysno(String sno);

    @Cacheable(key = "#p0")
    Student queryStudentBySno(String sno);

    List<Map<String, Object>> queryStudentsListMap();
}
