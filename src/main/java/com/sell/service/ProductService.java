package com.sell.service;

import com.sell.dataobject.ProductInfo;
import com.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService  {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //增加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减少库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架操作
    ProductInfo onSale(String productId);
    //下架操作
    ProductInfo offSale(String productId);

}
