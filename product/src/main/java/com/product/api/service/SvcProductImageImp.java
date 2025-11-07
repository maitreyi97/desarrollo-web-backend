package com.product.api.service;

import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {
    @Autowired
    RepoProductImage repo;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in){
          try{
            if(in.getImage().startsWith("data:image")){
                int commaIndex = in.getImage().indexOf(',');
                if(commaIndex != -1){
                    in.setImage(in.getImage().substring(commaIndex + 1));
                }
            }
            
            byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
            String fileName = UUID.randomUUID().toString() + ".png";
            Path imagePath = Paths.get(uploadDir, "img", "product", fileName);
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageBytes);
            ProductImage productImage = new ProductImage();
            productImage.setProduct_id(in.getProduct_id());
            productImage.setImage("/uploads/img/product/" + fileName);
            productImage.setStatus(1);

            repo.save(productImage);
            return new ResponseEntity<>(new ApiResponse("Imagen del producto ha sido subida"), HttpStatus.OK);
        } catch(DataAccessException e){
            throw new DBAccessException(e);
        }
        catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la imagen del producto");
        }
    
    }
    

    public ResponseEntity<ApiResponse> deleteProductImage(Integer id, Integer product_image_id) {
        try{
            repo.disableProductImage(product_image_id);
        return new ResponseEntity<>(new ApiResponse("Imagen del producto ha sido eliminada"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    public ResponseEntity<ProductImage[]> getProductImages(Integer product_id) {
        try {
            ProductImage[] productImgs = repo.findByProductId(product_id);
            if(productImgs == null || productImgs.length == 0){
                throw new ApiException(HttpStatus.BAD_REQUEST, "No hay imagenes asociadas al producto.");
            }
            return new ResponseEntity<>(productImgs, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}
