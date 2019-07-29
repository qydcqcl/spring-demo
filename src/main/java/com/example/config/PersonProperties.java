package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hzq
 * @date 2019/7/27 0027 下午 4:35
 */
@Component
public class PersonProperties {

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
