package com.zongyu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zongyu.cloud.enums.ReturnCodeEnum;
import com.zongyu.cloud.model.PayDTO;
import com.zongyu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Tag(name = "订单微服务模块", description = "订单CRUD")
public class OrderController {

    public static final String PAYMENT_SRV_URL = "http://localhost:8001";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping(value = "/consumer/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        // delete, entity直接设为null即可
        ResponseEntity<ResultData> resultEntity = restTemplate.exchange(PAYMENT_SRV_URL + "/pay/delete/" + id, HttpMethod.DELETE, null, ResultData.class, new HashMap<>());
        if (resultEntity != null) {
            return resultEntity.getBody();
        } else {
            log.error("result is null");
        }
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }

    @PutMapping(value = "/consumer/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法，JSON串做参数")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 发送请求
        HttpEntity<PayDTO> entity = new HttpEntity<>(payDTO, headers);
        // put请求
        ResponseEntity<ResultData> resultEntity = restTemplate.exchange(PAYMENT_SRV_URL + "/pay/update", HttpMethod.PUT, entity, ResultData.class, new HashMap<>());
        if (resultEntity != null) {
            return resultEntity.getBody();
        } else {
            log.error("result is null");
        }
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    @Operation(summary = "获取指定id支付流水", description = "获取单条支付流水")
    public ResultData getById(@PathVariable("id") Integer id) {
        // 测试全局异常使用
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }

        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class);
    }

    //
    @GetMapping(value = "/consumer/pay/getAll")
    @Operation(summary = "获取全部支付流水", description = "获取多条支付流水")
    public ResultData getAll() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/getAll", ResultData.class);
    }
//

    /**
     * 不使用全局异常，手动写异常demo
     *
     * @return
     */
    @GetMapping(value = "/consumer/pay/error")
    public ResultData<Integer> error() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/error", ResultData.class);
    }
}
