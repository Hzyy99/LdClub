package com.black.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *统一线程池
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 自定义label线程池
     * @return
     */
    @Bean("labelThreadPool")
    public ThreadPoolTaskExecutor getThreadPoolExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(200);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setThreadFactory(new CustomNameThreadFactory("labelThreadPool"));
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
