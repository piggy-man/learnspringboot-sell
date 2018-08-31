package com.sell.service.serviceImpl;

import com.sell.dataobject.OrderDetail;
import com.sell.dto.OrderDTO;
import com.sell.repository.OrderMasterRepository;
import com.sell.service.OrderService;
import com.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductService productService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //查询商品价格

        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            productService.findOne(orderDetail.getProductId());
        }

        //计算总价

        //写入订单数据库（两张表OrderMaster OrderDetail）

        //扣库存
        return OrderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> findOrderList(String buyerId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
