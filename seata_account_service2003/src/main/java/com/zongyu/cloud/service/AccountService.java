package com.zongyu.cloud.service;

public interface AccountService {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    void decrease(Long userId, Long money);
}
