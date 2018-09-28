package com.sell.service.serviceImpl;

import com.sell.dataobject.ProductInfo;
import com.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
@Autowired ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo result=productService.findOne("8a4f96da6579f233016579f23b2b0000");
        Assert.assertEquals("8a4f96da6579f233016579f23b2b0000",result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list=productService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest request= PageRequest.of(0,2);
        Page<ProductInfo> list=productService.findAll(request);
        System.out.println(list.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductDsp("好吃的虾");
        productInfo.setProductIcon("http://*****");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfo.setCategoryType(4);
        productInfo.setProductStock(100);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        ProductInfo result=productService.save(productInfo);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo=productService.onSale("123456");
        Assert.assertEquals(ProductStatusEnum.UP,productInfo.getProductStatus());
    }

    @Test
    public void offSale(){
        ProductInfo productInfo=productService.offSale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatus());
    }
}