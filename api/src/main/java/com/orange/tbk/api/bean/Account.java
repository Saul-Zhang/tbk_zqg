package com.orange.tbk.api.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@TableName("t_account")
@KeySequence("SEQ_TEST")
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "adzone_id", type = IdType.ID_WORKER_STR)
    @JSONField(name = "adzone_id")
    private String adzoneId;

    /**
     * 当前的积分
     */
    @JSONField(name = "integral")
    private String integral;

    /**
     * 可提现金额
     */
    @JSONField(name = "available_fee")
    private String availableFee;

    /**
     * 即将到帐
     */
    @JSONField(name = "pre_fee")
    private String preFee;

    /**
     * 累计收益
     */
    @JSONField(name = "total_fee")
    private String totalFee;
}
