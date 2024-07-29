package com.black.subject.application.interceptor;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fegin拦截器配置
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }

}
