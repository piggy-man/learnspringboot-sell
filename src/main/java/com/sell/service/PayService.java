package com.sell.service;

import com.sell.dto.OrderDTO;

public interface PayService {
    void create(OrderDTO orderDTO);
}
