package com.sell.repository;

import com.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    ProductCategory findByCategoryId(Integer Id);
    List<ProductCategory> findByCategoryTypeIn(List<Integer> cagetoryTypeList);

}
