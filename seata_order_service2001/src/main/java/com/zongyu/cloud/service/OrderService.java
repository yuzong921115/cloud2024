package com.zongyu.cloud.service;

import com.zongyu.cloud.model.Order;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param order
     */
    void create(Order order);
}
