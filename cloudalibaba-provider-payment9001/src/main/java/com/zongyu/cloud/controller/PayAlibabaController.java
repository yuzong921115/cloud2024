package com.zongyu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zongyu.cloud.enums.ReturnCodeEnum;
import com.zongyu.cloud.model.PayExtDTO;
import com.zongyu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PayAlibabaController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry,server port:" + serverPort + "\t,id=" + id;
    }

    /**
     * openFeign+sentinel进行服务降级和流量监控的整合处理
     * fallback放到openFeign统一处理
     */
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询返回的数据
        PayExtDTO payExtDTO = new PayExtDTO();
        payExtDTO.setId(1024);
        payExtDTO.setOrderNo(orderNo);
        payExtDTO.setAmount(BigDecimal.valueOf(9.9));
        payExtDTO.setPayNo("pay" + IdUtil.simpleUUID());
        payExtDTO.setUserId(1);
        return ResultData.success(payExtDTO);
    }

    public ResultData handlerBlockHandler(@PathVariable("orderNo") String orderNo, BlockException e) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "getPayByOrderNo服务不可用，触发Sentinel流控规则配置");
    }
}
