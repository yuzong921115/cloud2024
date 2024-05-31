package com.zongyu.cloud.apis;

import com.zongyu.cloud.enums.ReturnCodeEnum;
import com.zongyu.cloud.resp.ResultData;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机，fallback服务降级");
    }
}
