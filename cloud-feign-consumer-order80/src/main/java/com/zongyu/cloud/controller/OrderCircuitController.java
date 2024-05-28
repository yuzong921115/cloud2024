package com.zongyu.cloud.controller;

import com.zongyu.cloud.apis.PayFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignClient payFeignClient;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable("id") Integer id) {
        return payFeignClient.myCircuit(id);
    }

    public String myCircuitFallback(Throwable t) {
        return "myCircuitFallback , 系统服务繁忙，亲稍后再试";
    }

    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback2", type = Bulkhead.Type.SEMAPHORE)
    public String myCircuit2(@PathVariable("id") Integer id) {
        return payFeignClient.myCircuit2(id);
    }

    public String myCircuitFallback2(Throwable t) {
        return "myCircuitFallback bulkhead, 系统服务繁忙，亲稍后再试";
    }
}
