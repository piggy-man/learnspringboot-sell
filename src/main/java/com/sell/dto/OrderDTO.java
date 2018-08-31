package com.sell.dto;

import com.sell.dataobject.OrderDetail;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data

public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenId;

    private BigDecimal orderAmount;

    private Integer orderStatus ;

    private Integer payStatus ;

    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;

    @org.hibernate.annotations.UpdateTimestamp
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
