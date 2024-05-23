package com.zongyu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zongyu.cloud.enums.ReturnCodeEnum;
import com.zongyu.cloud.model.Pay;
import com.zongyu.cloud.model.PayBaseDTO;
import com.zongyu.cloud.model.PayExtDTO;
import com.zongyu.cloud.resp.ResultData;
import com.zongyu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public ResultData<String> addPay(@RequestBody PayBaseDTO payBaseDTO) {
        log.info("接收参数：{}", payBaseDTO.toString());
        Pay pay = new Pay();
        BeanUtil.copyProperties(payBaseDTO, pay);
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping(value = "/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法，JSON串做参数")
    public ResultData<String> updatePay(@RequestBody PayExtDTO payExtDTO) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(payExtDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "获取指定id支付流水", description = "获取单条支付流水")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        // 测试全局异常使用
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }

        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "获取全部支付流水", description = "获取多条支付流水")
    public ResultData<List<Pay>> getAll() {
        List<Pay> list = payService.getAll();
        return ResultData.success(list);
    }

    /**
     * 不使用全局异常，手动写异常demo
     *
     * @return
     */
    @GetMapping(value = "/pay/error")
    public ResultData<Integer> error() {
        Integer integer = Integer.valueOf(200);
        try {
            int age = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(integer);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public String info(@Value("${zongyu.info}") String zongyuInfo) {
        return "consul:" + zongyuInfo + "，端口号：" + port;
    }
}
