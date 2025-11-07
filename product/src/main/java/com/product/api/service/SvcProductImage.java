package com.product.api.service;

import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {
    public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);
    public ResponseEntity<ApiResponse> deleteProductImage(Integer id, Integer product_image_id);
    public ResponseEntity<ProductImage[]> getProductImages(Integer id); 
}
