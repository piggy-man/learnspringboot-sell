package com.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sell.dataobject.OrderDetail;

import com.sell.utils.serialize.Date2Serializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonSerialize(using = Date2Serializer.class)
    private Date createTime;

    @org.hibernate.annotations.UpdateTimestamp
    @JsonSerialize(using = Date2Serializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
