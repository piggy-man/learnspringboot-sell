package com.sell.service.serviceImpl;

import com.sell.dataobject.OrderDetail;
import com.sell.dto.OrderDTO;
import com.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String OPENID ="110110";
    private final String ORDERID="1536029661695916679";

    @Test
    public void createOrder() {

        OrderDTO orderDTO =new OrderDTO();
        orderDTO.setBuyerName("魏来来");
        orderDTO.setBuyerOpenId(OPENID);
        orderDTO.setBuyerPhone("13570384008");

        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("1234568");
        orderDetail.setProductQuantity(100);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail1=new OrderDetail();
        orderDetail1.setProductId("123457");
        orderDetail1.setProductQuantity(100);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result=orderService.createOrder(orderDTO);
        log.info("【创建订单】result={}",result);


    }

    @Test
    public void findOne() {
        OrderDTO orderDTO=orderService.findOne(ORDERID);
        log.info("【查询单个订单】result={}",orderDTO);
        Assert.assertEquals(ORDERID,orderDTO.getOrderId());
    }

    @Test
    public void findOrderList() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findOrderList(OPENID,pageRequest);
        Assert.assertNotEquals(null,orderDTOPage);
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}