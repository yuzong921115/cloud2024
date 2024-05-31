package com.zongyu.cloud.service.impl;

import com.zongyu.cloud.mapper.AccoutMapper;
import com.zongyu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccoutMapper accoutMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("account service开始扣减余额");
        accoutMapper.decrease(userId, money);
//        myTimeOut();
//        int age = 10 / 0;
        log.info("account service结束扣减余额");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
