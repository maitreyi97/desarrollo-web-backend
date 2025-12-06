package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;
import com.product.exception.ApiException;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Image", description = "Operaciones para gestionar imágenes de productos")
@RestController
@RequestMapping("/product/{id}/image")
public class CtrlProductImage {
    @Autowired
    SvcProductImage svc;

    @GetMapping
    @Operation(summary = "Enlistar imágenes de producto", description = "GET /product/{id}/image - Retorna las imágenes asociadas al producto {id}")
    public ResponseEntity<ProductImage[]> getProductImages(@PathVariable("id") Integer id) {
        return svc.getProductImages(id);
    }

    @PostMapping
    @Operation(summary = "Subir imagen de producto", description = "POST /product/{id}/image - Sube una nueva imagen para el producto {id}. El body debe contener los datos de la imagen.")
    public ResponseEntity<ApiResponse> createProductImage(@PathVariable("id") Integer id,
            @Valid @RequestBody DtoProductImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
        }
        in.setProduct_id(id);
        return svc.uploadProductImage(in);
    }

    @DeleteMapping("/{product_image_id}")
    @Operation(summary = "Eliminar imagen de producto", description = "DELETE /product/{id}/image/{product_image_id} - Elimina la imagen {product_image_id} del producto {id}")
    public ResponseEntity<ApiResponse> deleteProductImage(@PathVariable("id") Integer product_id,
            @PathVariable("product_image_id") Integer product_image_id) {
        return svc.deleteProductImage(product_id, product_image_id);
    }
}
