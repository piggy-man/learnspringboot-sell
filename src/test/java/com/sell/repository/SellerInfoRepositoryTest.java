package com.sell.repository;

import com.sell.dataobject.SellerInfo;
import com.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenId("abc");
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setSellerId(KeyUtil.KeyUniqueUtil());
        SellerInfo result=sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenId() {

        SellerInfo result=sellerInfoRepository.findByOpenId("abc");
        Assert.assertNotNull(result);
    }
}