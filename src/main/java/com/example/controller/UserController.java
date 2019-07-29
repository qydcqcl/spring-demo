package com.example.controller;

import com.example.bean.User;
import com.example.service.UserServiceI;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 4:38
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @GetMapping("findByName")
    public User getUserByName(String name) {
        return userService.findByName(name);
    }

    @PostMapping("save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @RequestMapping("serialization")
    @ResponseBody
    public String serialization() {
        try {
            User user = new User();
            user.setUserName("mrbird");
            user.setBirthday(new Date());
            String str = objectMapper.writeValueAsString(user);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * readTree方法可以接受一个字符串或者字节数组、文件、InputStream等，
     * 返回JsonNode作为根节点，你可以像操作XML DOM那样操作遍历JsonNode以获取数据。
     * @return
     */
    @ResponseBody
    @RequestMapping("readjsonstring")
    public String readJsonString() {
        try {
            String json = "{\"name\":\"mrbird\",\"age\":26}";
            JsonNode node = objectMapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Java对象和JSON数据进行绑定
     * @return
     */
    @ResponseBody
    @RequestMapping("readjsonasobject")
    public String readJsonAsObject() {
        try {
            String json = "{\"name\":\"mrbird\",\"age\":26}";
            User user = objectMapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("getUser")
    @JsonView(User.UserNameView.class)
    public User getUser() {
        User user = new User();
        user.setUserName("mrbird");
        user.setBirthday(new Date());
        return user;
    }

    @ResponseBody
    @RequestMapping("customize")
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
//        List<User> list = objectMapper.readValue(jsonStr, List.class);
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = objectMapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("updateuser")
    public int updateUser(@RequestBody List<User> list){
        return list.size();
    }
}
