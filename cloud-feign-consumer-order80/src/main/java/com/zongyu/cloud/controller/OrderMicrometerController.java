package com.zongyu.cloud.controller;

import com.zongyu.cloud.apis.PayFeignClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderMicrometerController {
    @Resource
    private PayFeignClient payFeignClient;

    @GetMapping(value = "/feign/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return payFeignClient.myMicrometer(id);
    }
}
