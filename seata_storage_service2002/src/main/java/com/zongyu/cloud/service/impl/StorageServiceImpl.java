package com.zongyu.cloud.service.impl;

import com.zongyu.cloud.mapper.StorageMapper;
import com.zongyu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("storage service开始扣减库存");
        storageMapper.decrease(productId, count);
        log.info("storage service结束扣减库存");
    }
}
