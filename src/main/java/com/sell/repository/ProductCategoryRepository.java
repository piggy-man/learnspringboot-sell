package com.sell.repository;

import com.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    ProductCategory findByCategoryId(Integer Id);

}
