package com.zongyu.cloud.controller;

import com.zongyu.cloud.apis.PayFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignClient payFeignClient;

    /**
     * 熔断+降级示例
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable("id") Integer id) {
        return payFeignClient.myCircuit(id);
    }

    public String myCircuitFallback(Throwable t) {
        return "myCircuitFallback , 系统服务繁忙，亲稍后再试";
    }

    /**
     * 舱壁 信号量
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback2", type = Bulkhead.Type.SEMAPHORE)
    public String myCircuit2(@PathVariable("id") Integer id) {
        return payFeignClient.myCircuit2(id);
    }

    public String myCircuitFallback2(Throwable t) {
        return "myCircuitFallback bulkhead, 系统服务繁忙，亲稍后再试";
    }

    /**
     * 舱壁 线程池
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/pool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myCircuitFallbackPool", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> bulkheadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + " come in...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + " leave...");
        return CompletableFuture.supplyAsync(() -> payFeignClient.myCircuit2(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> myCircuitFallbackPool(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "myCircuitFallback bulkhead pool, 系统服务繁忙，亲稍后再试");
    }
}
