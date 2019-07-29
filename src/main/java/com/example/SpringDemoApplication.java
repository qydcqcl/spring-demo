package com.example;

import com.example.config.ConfigBean;
import com.example.config.TestConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * @EnableCaching 开启缓存
 */
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class, TestConfigBean.class})
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);

		// 关闭banner.txt 启动图标
//		SpringApplication app = new SpringApplication(SpringDemoApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
	}

}
