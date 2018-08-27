package com.sell.repository;

import com.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
    }

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductDsp("好喝的粥");
        productInfo.setProductIcon("http://*****");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setCategoryType(2);
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);

        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);


    }
}