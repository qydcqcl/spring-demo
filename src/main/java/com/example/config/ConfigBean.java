package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hzq
 * @date 2019/7/27 0027 下午 4:40
 */
@ConfigurationProperties(prefix = "person")
public class ConfigBean {

    private String name;
    private int age;

    private String all;

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

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
