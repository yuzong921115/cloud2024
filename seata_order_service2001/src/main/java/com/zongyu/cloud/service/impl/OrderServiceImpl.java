package com.zongyu.cloud.service.impl;

import com.zongyu.cloud.apis.AccountFeignApi;
import com.zongyu.cloud.apis.StorageFeignApi;
import com.zongyu.cloud.mapper.OrderMapper;
import com.zongyu.cloud.model.Order;
import com.zongyu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignApi storageFeignApi;
    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
//    @GlobalTransactional
    public void create(Order order) {
        // 全局事务ID（xid）的检查，重要
        String xid = RootContext.getXID();
        log.info("------------开始新建订单xid:{}", xid);
        // 1.新建订单
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        Order orderFromDB = null;
        if (result > 0) {
            // 从mysql查询到刚插入的记录
            orderFromDB = orderMapper.selectOne(order);
            log.info("新建订单成功，orderFromDB info:{}", orderFromDB);

            // 2.扣减库存
            log.info("订单开始调用storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("订单开始调用storage库存，扣减完成");

            // 3.扣减账户余额
            log.info("订单开始调用account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("订单开始调用account账号，扣减完成");

            // 4.修改订单状态
            log.info("开始修改订单状态");
            orderFromDB.setStatus(1);

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updataResult = orderMapper.updateByExample(orderFromDB, whereCondition);
            log.info("结束修改订单状态:{}", updataResult);
            log.info("orderFromDB info:{}", orderFromDB);
        }
        log.info("------------结束新建订单xid:{}", xid);
    }
}
