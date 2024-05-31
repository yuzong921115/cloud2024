package com.zongyu.cloud.mapper;

import com.zongyu.cloud.model.Accout;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

public interface AccoutMapper extends Mapper<Accout> {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}