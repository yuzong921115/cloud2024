package com.zongyu.cloud.service;

import com.zongyu.cloud.model.Pay;

import java.util.List;

public interface PayService {
    int add(Pay pay);

    int delete(Integer id);

    int update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
