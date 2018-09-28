package com.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sell.enums.ProductStatusEnum;
import com.sell.service.CategoryService;
import com.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
//@GenericGenerator(name = "system-uuid",strategy = "uuid")
public class ProductInfo {


    @Id
    @GeneratedValue
    private Integer id;
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
    @JsonIgnore
    public ProductStatusEnum getProductStatus(){
        return EnumUtil.findByCode(productStatus,ProductStatusEnum.class);
    }


}
