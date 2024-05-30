package com.zongyu.cloud.controller;

import com.zongyu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "super.toString() AA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "super.toString() BBBB";
    }

    /**
     * 测试链路限流 C受限 D正常
     *
     * @return
     */
    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "super.toString() C";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "super.toString() DDDDDD";
    }

    /**
     * 流控 排队等待
     */
    @GetMapping("/testE")
    public void testE() {
        System.out.println("排队等待testE");
    }
}
