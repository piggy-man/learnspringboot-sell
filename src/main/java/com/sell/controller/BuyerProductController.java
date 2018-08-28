package com.sell.controller;

import com.sell.VO.ProductInfoVO;
import com.sell.VO.ProductVO;
import com.sell.VO.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        resultVO.setCode(0);
        resultVO.setMsg("成功");
        List<ProductVO> datalist = Arrays.asList(productVO);
        resultVO.setData(datalist);

        List<ProductInfoVO> foodslist = Arrays.asList(productInfoVO);
        productVO.setFoods(foodslist);




        return resultVO;
    }
}
