package com.zongyu.cloud.mapper;

import com.zongyu.cloud.model.Accout;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccoutMapper extends Mapper<Accout> {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}