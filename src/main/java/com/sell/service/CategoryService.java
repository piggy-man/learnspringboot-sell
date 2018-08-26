package com.sell.service;

import com.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeListIn(List<Integer> productCategoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
