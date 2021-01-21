package com.orange.tbk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.bean.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT\n" +
            "\tsum(\n" +
            "\tIF\n" +
            "\t( tk_status = 3, pub_share_pre_fee_user, 0 )) available_fee,\n" +
            "\tsum(\n" +
            "\tIF\n" +
            "\t( tk_status = 12, pub_share_pre_fee_user, 0 )) pre_fee,\n" +
            "\tsum(\n" +
            "\tIF\n" +
            "\t( is_over = 1, pub_share_pre_fee_user, 0 )) total_fee,\n" +
            "\tadzone_id \n" +
            "FROM\n" +
            "\tt_order \n" +
            "GROUP BY\n" +
            "\tadzone_id")
    List<Account> getOrderFee();
}
