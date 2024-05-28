package com.zongyu.cloud.apis;

import com.zongyu.cloud.model.PayBaseDTO;
import com.zongyu.cloud.model.PayExtDTO;
import com.zongyu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignClient {

    @PostMapping(value = "/pay/add")
    ResultData addPay(@RequestBody PayBaseDTO payBaseDTO);

    @DeleteMapping(value = "/pay/delete/{id}")
    ResultData deletePay(@PathVariable("id") Integer id);

    @PutMapping(value = "/pay/update")
    ResultData updatePay(@RequestBody PayExtDTO payExtDTO);

    @GetMapping(value = "/pay/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    ResultData getAll();

    /**
     * 不使用全局异常，手动写异常demo
     *
     * @return
     */
    @GetMapping(value = "/pay/error")
    ResultData error();

    @GetMapping(value = "/pay/get/info")
    String info();

    /**
     * resilience4j circuit breaker 示例
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    String myCircuit2(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRateLimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);
}
