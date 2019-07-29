package com.example.bean;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @JsonIgnoreProperties，忽略一组属性，作用于类上
 * @author hzq
 * @date 2019/7/28 0028 下午 4:36
 */
@JsonIgnoreProperties({ "password", "age" })
@JsonSerialize(using = UserSerializer.class)
@JsonDeserialize(using = UserDeserializer.class)
public class User implements Serializable {

    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView {};
    public interface AllUserFieldView extends UserNameView {};

    private int id;

    /**
     * @JsonView，作用在类或者属性上，用来定义一个序列化组。 比如对于User对象，某些情况下只返回userName属性就行，
     * 而某些情况下需要返回全部属性
     */
    @JsonView(UserNameView.class)
    private String userName;

    private int age;

    /**
     * @Jsonlgnore，作用在属性上，用来忽略此属性。
     */
    @JsonIgnore
    private String password;

    /**
     * @JsonProperty，作用在属性上，用来为JSON Key指定一个别名.
     * @JsonFormat，用于日期格式化
     */
    @JsonProperty("bth")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
