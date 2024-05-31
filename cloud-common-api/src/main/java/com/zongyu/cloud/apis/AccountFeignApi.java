package com.zongyu.cloud.apis;

import com.zongyu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    @GetMapping(value = "/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
