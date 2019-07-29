package com.example;

import com.example.bean.Student;
import com.example.bean.User;
import com.example.service.StudentServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

/**
 * JUnit4中包含了几个比较重要的注解：@BeforeClass、@AfterClass、@Before、@After和@Test。
 * 其中，@BeforeClass和@AfterClass在每个类加载的开始和结束时运行，必须为静态方法；
 * 而@Before和@After则在每个测试方法开始之前和结束之后运行。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemoApplicationTests {

    /**
     * 初始化mockMvc
     */
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentServiceI studentService;

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("before class test");
    }

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        Student student1 = studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

//        student1.setName("康康");
//        studentService.update(student1);

        Student student2 = studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }


    @Test
    public void test2() throws Exception {
        // 模拟一个get请求
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}", "mrbird"));

        // 模拟一个post请求
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));

        // 模拟文件上传
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file("file", "文件内容".getBytes("utf-8")));

        // 模拟发送一个message参数，值为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));

        // 模拟提交一个checkbox值，name为hobby，值为sleep和eat
        mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));

        // 直接使用MultiValueMap构建参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", "mrbird");
        params.add("hobby", "sleep");
        params.add("hobby", "eat");
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));

        // 模拟发送JSON参数
        String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));

        //使用jackson转换json数据
        User user = new User();
        user.setUserName("Dopa");
        user.setPassword("ac3af72d9f95161a502fd326865c2f15");
        user.setAge(18);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));

        // 模拟Session和Cookie
        mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr("name", "value"));
        mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie("name", "value")));

        // 设置请求的Content-Type
        mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));

        // 设置返回格式为JSON
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));

        // 模拟HTTP请求头
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header("name", "values"));

        // 期望成功调用，即HTTP Status为200
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 期望返回内容是application/json
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // 检查返回JSON数据中某个值的内容
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mrbird"));

        // 判断Controller方法是否返回某视图
        mockMvc.perform(MockMvcRequestBuilders.post("/index"))
                .andExpect(MockMvcResultMatchers.view().name("index.html"));

        // 比较Model
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("password"))
                .andExpect(MockMvcResultMatchers.model().attribute("username", "mrbird"));

        // 比较forward或者redirect
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index.html"));
        // 或者
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));

        // 比较返回内容，使用content()
        // 返回内容为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().string("hello"));

        // 返回内容是XML，并且与xmlCotent一样
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().xml("xmlContent"));

        // 返回内容是JSON ，并且与jsonContent一样
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().json("jsonContent"));

        // 输出响应结果
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andDo(MockMvcResultHandlers.print());
    }
}
