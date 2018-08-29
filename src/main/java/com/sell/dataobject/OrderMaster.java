package com.sell.dataobject;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public class OrderMaster {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenId;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;

    @org.hibernate.annotations.UpdateTimestamp
    private Date updateTime;


}
