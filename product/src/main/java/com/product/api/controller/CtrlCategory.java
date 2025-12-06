package com.product.api.controller;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Clase CtrlCategory que actúa como controlador
 */
@Tag(name = "Category", description = "Operaciones para gestionar categorías")
@RestController
@RequestMapping("/category")
public class CtrlCategory {
    @Autowired
    SvcCategory svc;

    /**
     * Método para obtener las categorías
     * 
     * @return Lista de categorías
     */
    @GetMapping
    @Operation(summary = "Enlistar categorías", description = "GET /category - Retorna todas las categorías registradas")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/active")
    @Operation(summary = "Enlistar categorías activas", description = "GET /category/active - Retorna las categorías marcadas como activas")
    public ResponseEntity<List<Category>> findActive() {
        return ResponseEntity.ok(svc.findActive());
    }

    @PostMapping()
    @Operation(summary = "Crear categoría", description = "POST /category - Crea una nueva categoría con los datos enviados en el body")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoCategoryIn in) {
        return ResponseEntity.ok(svc.create(in));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "PUT /category/{id} - Actualiza la categoría identificada por {id} con los datos del body")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in,
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(svc.update(in, id));
    }

    @PatchMapping("/{id}/enable")
    @Operation(summary = "Habilitar categoría", description = "PATCH /category/{id}/enable - Habilita la categoría indicada por {id}")
    public ResponseEntity<ApiResponse> enable(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.enable(id));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Deshabilitar categoría", description = "PATCH /category/{id}/disable - Deshabilita la categoría indicada por {id}")
    public ResponseEntity<ApiResponse> disable(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.disable(id));
    }
}