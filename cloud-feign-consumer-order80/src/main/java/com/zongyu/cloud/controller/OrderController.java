package com.zongyu.cloud.controller;

import com.zongyu.cloud.apis.PayFeignClient;
import com.zongyu.cloud.model.PayBaseDTO;
import com.zongyu.cloud.model.PayExtDTO;
import com.zongyu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "订单微服务模块", description = "订单CRUD")
public class OrderController {
    @Resource
    private PayFeignClient payFeignClient;

    @PostMapping(value = "/feign/consumer/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public ResultData addOrder(@RequestBody PayBaseDTO payBaseDTO) {
        return payFeignClient.addPay(payBaseDTO);
    }

    @DeleteMapping(value = "/feign/consumer/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        return payFeignClient.deletePay(id);
    }

    @PutMapping(value = "/feign/consumer/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法，JSON串做参数")
    public ResultData updateOrder(@RequestBody PayExtDTO payExtDTO) {
        return payFeignClient.updatePay(payExtDTO);
    }

    @GetMapping(value = "/feign/consumer/pay/get/{id}")
    @Operation(summary = "获取指定id支付流水", description = "获取单条支付流水")
    public ResultData getById(@PathVariable("id") Integer id) {
        log.info("开始调用。。。。。");
        ResultData resultData = null;
//        try {
//            log.info("开始调用1。。。。。{}", DateUtil.now());
            resultData = payFeignClient.getById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("开始调用2。。。。。{}", DateUtil.now());
//            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
//        }
        return resultData;
    }

    @GetMapping(value = "/feign/consumer/pay/getAll")
    @Operation(summary = "获取全部支付流水", description = "获取多条支付流水")
    public ResultData getAll() {
        return payFeignClient.getAll();
    }

    /**
     * 不使用全局异常，手动写异常demo
     *
     * @return
     */
    @GetMapping(value = "/feign/consumer/pay/error")
    public ResultData error() {
        return payFeignClient.error();
    }

    @GetMapping(value = "/feign/consumer/pay/get/info")
    public String info() {
        return payFeignClient.info();
    }
}
