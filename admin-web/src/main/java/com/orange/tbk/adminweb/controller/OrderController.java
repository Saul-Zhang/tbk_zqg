package com.orange.tbk.adminweb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.tbk.adminweb.annotation.JwtIgnore;
import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.api.bean.Order;
import com.orange.tbk.api.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference(version = "${admin.version}", check = false)
    private OrderService orderService;

    @GetMapping("info")
    @JwtIgnore
    public Response<IPage<Order>> getInfo(String adzoneId, String fromInfo, Integer state,Page<Order> page) {
        IPage<Order> pageOrder = null;
        if ("tb".equals(fromInfo)) {
            pageOrder = orderService.getInfo(adzoneId,state,page);
        }
        return Response.build(ResponseCode.QUERY_SUCCESS, pageOrder);
    }

}
