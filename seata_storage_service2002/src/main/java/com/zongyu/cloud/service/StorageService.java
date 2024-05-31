package com.zongyu.cloud.service;

public interface StorageService {

    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    void decrease(Long productId, Integer count);

}
