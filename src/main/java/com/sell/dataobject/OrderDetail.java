package com.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
//@GenericGenerator(name = "system-uuid", strategy = "uuid")
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;

    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;

    @org.hibernate.annotations.UpdateTimestamp
    private Date updateTime;


}
