package com.black.subject.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *Subject启动类
 */
@SpringBootApplication
@ComponentScan("com.black.subject")
@MapperScan("com.black.subject.infra.**.mapper")
@EnableFeignClients(basePackages = "com.black")
public class SubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class);
    }

}