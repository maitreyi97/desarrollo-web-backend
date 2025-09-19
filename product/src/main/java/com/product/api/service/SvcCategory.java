package com.product.api.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.product.api.entity.Category;

/**
 * Interfaz SvcCategory que define el servicio para las categorías
 */
public interface SvcCategory {
    public ResponseEntity<List<Category>> getCategories();
}
