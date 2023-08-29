package com.biabuluo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 小宇
 * @date {2023}-{08}-{24}:{23:20}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: Springboot 启动类
 */

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class EaseBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(EaseBlogApplication.class);
    }
}
