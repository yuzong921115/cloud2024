package com.zongyu.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "super.toString() AA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "super.toString() BBBB";
    }
}
