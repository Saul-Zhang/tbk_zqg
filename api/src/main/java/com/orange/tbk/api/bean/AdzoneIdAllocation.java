package com.orange.tbk.api.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_adzoneId_allocation")
@Data
@Entity
public class AdzoneIdAllocation {
    @Id
    private Integer id;

    @Column(name = "adzone_id",length = 20)
    private String adzoneId;

    @Column(name = "status" ,length = 2)
    private Integer status;
}
