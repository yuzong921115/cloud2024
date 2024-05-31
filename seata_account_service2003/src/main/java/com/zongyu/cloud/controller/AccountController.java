package com.zongyu.cloud.controller;

import com.zongyu.cloud.resp.ResultData;
import com.zongyu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping(value = "/account/decrease")
    public ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
        accountService.decrease(userId, money);
        return ResultData.success("扣减账户余额成功");
    }
}
