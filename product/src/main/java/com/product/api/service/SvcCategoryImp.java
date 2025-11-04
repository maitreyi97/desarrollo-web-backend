package com.product.api.service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Clase SvcCategoryImp que implementa la interfaz SvcCategory
 * con uso de Autowired para la inyección de dependencias
 */
@Service
public class SvcCategoryImp implements SvcCategory {
    @Autowired
    RepoCategory repo;

    @Override
    public List<Category> findAll() {
        try {
            return repo.findAll();
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public List<Category> findActive() {
        try {
            return repo.findActive();
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ApiResponse create(DtoCategoryIn in) {
        try {
            Category existing = repo.findByCategory(in.getCategory());
            if (existing != null) {
                throw new ApiException(org.springframework.http.HttpStatus.BAD_REQUEST,
                        "La categoria ya existe");
            }
            repo.create(in.getCategory(), in.getTag());
            return new ApiResponse("categoria creada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ApiResponse update(DtoCategoryIn in, Integer id) {
        try {
            Category byId = repo.findById(id).orElse(null);
            if (byId == null) {
                throw new ApiException(org.springframework.http.HttpStatus.NOT_FOUND, "categoria no encontrada");
            }
            Category byName = repo.findByCategory(in.getCategory());
            if (byName != null && !byName.getCategory_id().equals(id)) {
                throw new ApiException(org.springframework.http.HttpStatus.BAD_REQUEST, "La categoria ya existe");
            }
            repo.update(in.getCategory(), in.getTag(), id);
            return new ApiResponse("categoria actualizada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ApiResponse enable(Integer id) {
        try {
            Category byId = repo.findById(id).orElse(null);
            if (byId == null) {
                throw new ApiException(org.springframework.http.HttpStatus.NOT_FOUND, "categoria no encontrada");
            }
            if (byId.getStatus() != null && byId.getStatus() == 1) {
                return new ApiResponse("categoria ya estaba habilitada");
            }
            repo.enable(id);
            return new ApiResponse("categoria habilitada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public ApiResponse disable(Integer id) {
        try {
            Category byId = repo.findById(id).orElse(null);
            if (byId == null) {
                throw new ApiException(org.springframework.http.HttpStatus.NOT_FOUND, "categoria no encontrada");
            }
            if (byId.getStatus() != null && byId.getStatus() == 0) {
                return new ApiResponse("categoria ya estaba deshabilitada");
            }
            repo.disable(id);
            return new ApiResponse("categoria deshabilitada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

}