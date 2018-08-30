package com.sell.repository;

import com.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
@Autowired
private OrderDetailRepository orderDetailRepository;

@Test
public void saveTest(){
    OrderDetail orderDetail=new OrderDetail();
    orderDetail.setDetailId("123456789");
    orderDetail.setOrderId("11111");
    orderDetail.setProductIcon("http://*****");
    orderDetail.setProductId("1111112");
    orderDetail.setProductName("皮蛋粥");
    orderDetail.setProductPrice(new BigDecimal(5));
    OrderDetail result=orderDetailRepository.save(orderDetail);
    Assert.assertNotEquals(null,result);
}

    @Test
    public void findByOrderId() {
        List<OrderDetail> list=new ArrayList<>();
        list=orderDetailRepository.findByOrderId("11111");
        Assert.assertNotEquals(0,list.size());
    }
}