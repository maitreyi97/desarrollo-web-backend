package com.product.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.product.api.entity.ProductImage;


public interface RepoProductImage extends JpaRepository<ProductImage, Integer>{
    @Query(value = "SELECT * FROM product_image WHERE product_id = :product_id AND status = 1", nativeQuery = true)
    ProductImage[] findByProductId(Integer product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_image SET status = 0 WHERE product_image_id = :product_image_id", nativeQuery = true)
    void disableProductImage(@Param("product_image_id") Integer product_image_id);
}
