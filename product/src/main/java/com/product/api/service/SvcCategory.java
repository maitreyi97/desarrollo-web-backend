package com.product.api.service;

import java.util.List;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;

/**
 * Interfaz SvcCategory que define el servicio para las categorías
 */
public interface SvcCategory {
    public List<Category> findAll();

    public List<Category> findActive();

    public ApiResponse create(DtoCategoryIn in);

    public ApiResponse update(DtoCategoryIn in, Integer id);

    public ApiResponse enable(Integer id);

    public ApiResponse disable(Integer id);
}
