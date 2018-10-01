package com.sell.repository;

import com.sell.dataobject.ProductInfo;
import com.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList=productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(KeyUtil.KeyUniqueUtil());
        productInfo.setProductName("热干面");
        productInfo.setProductDsp("老家的味道");
        productInfo.setProductIcon("http://******");
        productInfo.setProductPrice(new BigDecimal(10));
        productInfo.setCategoryType(2);
        productInfo.setProductStock(1000);
        productInfo.setProductStatus(0);

        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);


    }
}