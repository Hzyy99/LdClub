package com.black.wechatLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信服务启动器
 */
@SpringBootApplication(scanBasePackages = {"com.black.wechatLogin"})
public class WxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class);
    }

}
