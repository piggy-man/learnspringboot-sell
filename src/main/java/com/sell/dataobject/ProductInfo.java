package com.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@GenericGenerator(name = "system-uuid",strategy = "uuid")
public class ProductInfo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 32)
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDsp;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;

    @org.hibernate.annotations.UpdateTimestamp
    private Date updateTime;

    public ProductInfo() {
    }
}
