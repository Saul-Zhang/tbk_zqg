package com.orange.tbk.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口默认控制配置
 * visit 1=可以访问 0=接口关闭
 * ipHandle 1开启ip限流控制 0关闭
 * ipVisits ip访问次数，限制接口在 xx 分钟内容只能访问 xx 次，默认60次
 * ipRedisInterval ip redis缓存多少分钟 默认1分钟
 */
public class InterfaceManagement implements Serializable {

    private String key;

    private String remarks;

    private Integer visit;

    private Integer ipHandle;

    private Long ipVisits;

    private Long ipRedisInterval;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Integer getIpHandle() {
        return ipHandle;
    }

    public void setIpHandle(Integer ipHandle) {
        this.ipHandle = ipHandle;
    }

    public Long getIpVisits() {
        return ipVisits;
    }

    public void setIpVisits(Long ipVisits) {
        this.ipVisits = ipVisits;
    }

    public Long getIpRedisInterval() {
        return ipRedisInterval;
    }

    public void setIpRedisInterval(Long ipRedisInterval) {
        this.ipRedisInterval = ipRedisInterval;
    }
}
