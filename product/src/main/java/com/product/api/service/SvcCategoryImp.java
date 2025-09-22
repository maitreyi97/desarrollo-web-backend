package com.product.api.service;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase SvcCategoryImp que implementa la interfaz SvcCategory
 * con uso de Autowired para la inyección de dependencias
 */
@Service
public class SvcCategoryImp implements SvcCategory{
    @Autowired
    RepoCategory repo;

    @Override
    public List<Category> getCategories() {
        return repo.getCategories();
    }
}