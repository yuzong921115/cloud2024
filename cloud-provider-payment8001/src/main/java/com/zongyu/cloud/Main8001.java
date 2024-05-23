package com.zongyu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.zongyu.cloud.mapper") // import tk.mybatis.spring.annotation.MapperScan
@EnableDiscoveryClient // 开启服务注册与发现
@RefreshScope // 动态刷新consul配置项
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}