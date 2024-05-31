package com.zongyu.cloud.apis;

import com.zongyu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @GetMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("userId") Integer count);
}
