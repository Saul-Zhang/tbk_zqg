package com.orange.tbk.api.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * t_order
 * @author Orange
 * @date 2020/10/31
 */
@Data
@TableName("t_order")
@KeySequence("SEQ_TEST")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 每个商品对应的订单编号，此订单编号并未在淘宝买家后台透出
     * trade_id
     */
    @TableId(value = "trade_id", type = IdType.ID_WORKER_STR)
    @JSONField(name = "trade_id")
    private String tradeId;

    /**
     * tb_paid_time
     */
    @JSONField(name = "tb_paid_time")
    private Date tbPaidTime;

    /**
     * 买家在淘宝后台显示的订单编号
     * trade_parent_id
     */
    @JSONField(name = "trade_parent_id")
    private String tradeParentId;

    /**
     * 商品标题
     * item_title
     */
    @JSONField(name = "item_title")
    private String itemTitle;

    /**
     * 商品图片
     * item_img
     */
    @JSONField(name = "item_img")
    private String itemImg;

    /**
     * 商品单价
     * item_price
     */
    @JSONField(name = "item_price")
    private String itemPrice;

    /**
     * 买家拍下付款的金额（不包含运费金额）
     * alipay_total_price
     */
    @JSONField(name = "alipay_total_price")
    private String alipayTotalPrice;

    /**
     * 	用户付款预估收入
     * pub_share_pre_fee_user
     */
    @JSONField(name = "pub_share_pre_fee_user")
    private String pubSharePreFeeUser;

    /**
     * 	付款预估收入=付款金额*提成
     * pub_share_pre_fee
     */
    @JSONField(name = "pub_share_pre_fee")
    private String pubSharePreFee;

    /**
     * 3：订单结算，12：订单付款， 13：订单失效，14：订单成功
     * tk_status
     */
    @JSONField(name = "tk_status")
    private Byte tkStatus;

    /**
     * 佣金比率
     * total_commission_rate
     */
    @JSONField(name = "total_commission_rate")
    private String totalCommissionRate;

    /**
     * 商品链接
     * item_link
     */
    @JSONField(name = "item_link")
    private String itemLink;

    /**
     * 推广位管理下的推广位名称对应的ID，同时也是pid=mm_1_2_3中的“3”这段数字
     * adzone_id
     */
    @JSONField(name = "adzone_id")
    private String adzoneId;

    /**
     * is_over
     */
    @JSONField(name = "is_over")
    private Integer isOver;

    /**
     * item_id
     */
    @JSONField(name = "item_id")
    private String itemId;

    /**
     * seller_shop_title
     */
    @JSONField(name = "seller_shop_title")
    private String sellerShopTitle;

}