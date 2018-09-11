package com.sell.service.serviceImpl;

import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.BuyerService;
import com.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    private OrderDTO checkBuyer(String orderId, String openid) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenId().equalsIgnoreCase(orderId)) {
            log.error("【检验买家】错误，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId, String openid) {

        return checkBuyer(orderId, openid);
    }

    @Override
    public OrderDTO cancel(String orderId, String openid) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            log.error("【买家取消订单】失败，查无此订单orderId={}", orderId);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }
}
