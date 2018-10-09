package com.sell.service.serviceImpl;

import com.sell.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {
    public static final String OPENID="abc";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findByOpenId() {

        SellerInfo sellerInfo=sellerService.findByOpenId(OPENID);
        Assert.assertEquals(sellerInfo.getOpenId(),OPENID);
    }
}