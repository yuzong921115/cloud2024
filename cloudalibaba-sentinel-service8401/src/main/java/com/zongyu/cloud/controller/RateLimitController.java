package com.zongyu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/pay/rateUrl")
    public String rateUrl() {
        return "按照Rest地址限流+默认限流返回";
    }

    @GetMapping("/pay/handler")
    @SentinelResource(value = "valPayHandler", blockHandler = "lockHandler")
    public String payHandler() {
        return "按SentinelResource资源名称限流+自定义限流返回";
    }

    public String lockHandler(BlockException blockException) {
        return "不可用";
    }

    @GetMapping("/pay/new/handler/{id}")
    @SentinelResource(value = "valNewPayHandler", blockHandler = "doActionBlockHandler", fallback = "doActionFallBack")
    public String payHandler(@PathVariable("id") Integer id) {
        if (id == 0) {
            throw new RuntimeException("不允许为0");
        }
        return "按SentinelResource资源名称限流+自定义限流返回+服务降级";
    }

    public String doActionBlockHandler(@PathVariable("id") Integer id, BlockException throwable) {
        return "触发sentinel规则";
    }

    public String doActionFallBack(@PathVariable("id") Integer id, Throwable throwable) {
        return "JVM抛出异常";
    }
}
