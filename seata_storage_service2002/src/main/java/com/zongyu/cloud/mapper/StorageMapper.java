package com.zongyu.cloud.mapper;

import com.zongyu.cloud.model.Storage;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {
    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    void decrease(@RequestParam("productId") Long productId, @RequestParam("userId") Integer count);
}