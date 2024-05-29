package com.zongyu.cloud.controller;

import com.zongyu.cloud.apis.PayFeignClient;
import com.zongyu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderGateWayController {
    @Resource
    private PayFeignClient payFeignClient;

    @GetMapping(value = "/feign/order/gateway/get/{id}")
    ResultData getByIdByGateWay(@PathVariable("id") Integer id) {
        return ResultData.success(payFeignClient.getByIdByGateWay(id));
    }

    @GetMapping(value = "/feign/order/gateway/info")
    ResultData infoByGateWay() {
        return ResultData.success(payFeignClient.infoByGateWay());
    }
}
