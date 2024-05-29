package com.zongyu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zongyu.cloud.model.Pay;
import com.zongyu.cloud.resp.ResultData;
import com.zongyu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayGateWayController {
    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/gateway/get/{id}")
    @Operation(summary = "获取指定id支付流水", description = "获取单条支付流水")
    public ResultData<Pay> getByIdByGateWay(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> infoByGateWay() {
        return ResultData.success("gateway info test:" + IdUtil.simpleUUID());
    }
}
