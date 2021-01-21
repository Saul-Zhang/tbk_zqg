package com.orange.tbk.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.tbk.admin.mapper.OrderMapper;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.bean.Order;
import com.orange.tbk.api.service.OrderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${version}")
public class OrderImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> getInfo(String adzoneId, Integer state, Page<Order> page) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("adzone_id", adzoneId);
        if (!state.equals(0)) {
            wrapper.eq("tk_status", state);
        }

        return this.page(page, wrapper);
    }

    @Override
    public Order selectById(String tradeId) {
        return super.getById(tradeId);
    }

    @Override
    public void insert(Order order) {
        super.save(order);
    }

    @Override
    public List<Account> getOrderFee() {
        return orderMapper.getOrderFee();
    }
}
