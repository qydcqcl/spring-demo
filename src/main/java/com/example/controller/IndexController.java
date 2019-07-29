package com.example.controller;

import com.example.config.ConfigBean;
import com.example.config.PersonProperties;
import com.example.config.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzq
 * @date 2019/7/27 0027 下午 4:15
 */
@RestController
public class IndexController {

    @Autowired
    private PersonProperties personProperties;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private TestConfigBean testConfigBean;

    @RequestMapping("/")
    public String index(){
        return testConfigBean.getName() + ", " + personProperties.getAge() + "," + configBean.getAll();
    }

}
