package com.orange.tbk.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.bean.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    IPage<Order> getInfo(String adzoneId, Integer state, Page<Order> page);

    Order selectById(String tradeId);

    void insert(Order order);

    List<Account> getOrderFee();
}
