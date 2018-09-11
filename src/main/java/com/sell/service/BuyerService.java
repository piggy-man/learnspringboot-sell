package com.sell.service;
import com.sell.dto.OrderDTO;

public interface BuyerService {
    //查询一个订单
    OrderDTO findOne(String orderId,String openid);
    //取消一个订单
    OrderDTO cancel(String orderId,String openid);

}
