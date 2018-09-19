package com.sell.service;

import com.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    // 查询订单列表
    Page<OrderDTO> findOrderList(String buyerId, Pageable pageable);
    // 取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);
    // 完结订单
    OrderDTO finish(OrderDTO orderDTO);
    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);

    Page<OrderDTO> findAll(Pageable pageable);
}
