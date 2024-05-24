package com.zongyu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        // Feign默认配置不走重试策略
//        return Retryer.NEVER_RETRY;

        // 最大请求次数为3（1+2），初始间隔时间为100ms，重试最大间隔时间为1s
        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
