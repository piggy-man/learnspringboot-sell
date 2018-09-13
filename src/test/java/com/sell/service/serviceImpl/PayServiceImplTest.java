package com.sell.service.serviceImpl;

import com.sell.dto.OrderDTO;
import com.sell.service.OrderService;
import com.sell.service.PayService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    private static String ORDERID="1536578527575781026";
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @Test
    public void create() {
        OrderDTO orderDTO=orderService.findOne(ORDERID);
        payService.create(orderDTO);

    }
}