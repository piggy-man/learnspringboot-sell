package com.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "seller_info")
@DynamicUpdate
public class SellerInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private String sellerId;

    private String username;

    private String password;

    private String openId;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public SellerInfo() {
    }
}
