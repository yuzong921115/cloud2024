package com.zongyu.cloud.mapper;

import com.zongyu.cloud.model.Storage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {
    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}