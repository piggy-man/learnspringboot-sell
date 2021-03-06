package com.sell.controller;

import com.sell.dataobject.ProductCategory;
import com.sell.dataobject.ProductInfo;
import com.sell.exception.SellException;
import com.sell.forms.ProductForm;
import com.sell.service.CategoryService;
import com.sell.service.ProductService;
import com.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
@DynamicUpdate
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String,Object> map){

        PageRequest pageRequest=PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage=productService.findAll(pageRequest);


      /*  List<Integer> productCategoryTypelist=productInfoPage.getContent().stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeListIn(productCategoryTypelist);
        */


        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        //map.put("productCategoryList",productCategoryList);
        return new ModelAndView("product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object>map){
        try{
            productService.onSale(productId);

        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        ProductInfo productInfo=productService.findOne(productId);
        if (!StringUtils.isEmpty(productInfo)){
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object>map){

        if (bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        if (productForm.getProductId().isEmpty()){
            ProductInfo productInfo=new ProductInfo();
            productForm.setProductId(KeyUtil.KeyUniqueUtil());
            BeanCopier beanCopier=BeanCopier.create(ProductForm.class,ProductInfo.class,false);
            beanCopier.copy(productForm,productInfo,null);
            ProductInfo productInfo1=productService.save(productInfo);
            log.info("新建对象={}",productInfo1);
        }else {

            try {
                ProductInfo productInfo1 = productService.findOne(productForm.getProductId());
                log.info("查找出来的商品是productInfo1={}", productInfo1);
                BeanCopier copier = BeanCopier.create(ProductForm.class, ProductInfo.class, false);
                copier.copy(productForm, productInfo1, null);
                productService.save(productInfo1);
            } catch (SellException e) {
                map.put("msg", e.getMessage());
                map.put("url", "sell/seller/product/index");
                return new ModelAndView("common/error", map);
            }
        }

            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/success",map);
        }



        //String productId=productForm.getProductId();
        //log.info("前端回传的隐藏productId={}",productId);
    }

