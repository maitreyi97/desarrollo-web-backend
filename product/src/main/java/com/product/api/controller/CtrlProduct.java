package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.service.SvcProduct;
import com.product.api.commons.dto.ApiResponse;
import com.product.exception.ApiException;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "Operaciones para gestionar productos")
@RestController
@RequestMapping("/product")
public class CtrlProduct {

	@Autowired
	SvcProduct svc;

	@GetMapping
	@Operation(summary = "Enlistar productos", description = "GET /product - Retorna la lista de productos (resumen para listado)")
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener producto", description = "GET /product/{id} - Retorna el detalle del producto identificado por {id}")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
		return svc.getProduct(id);
	}

	@PostMapping
	@Operation(summary = "Crear producto", description = "POST /product - Crea un nuevo producto con los datos enviados en el body")
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in) {
		return svc.createProduct(in);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar producto", description = "PUT /product/{id} - Actualiza el producto indicado por {id} con los datos del body")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in) {
		return svc.updateProduct(id, in);
	}

	@PatchMapping("/{id}/enable")
	@Operation(summary = "Habilitar producto", description = "PATCH /product/{id}/enable - Habilita el producto indicado por {id}")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProduct(id);
	}

	@PatchMapping("/{id}/disable")
	@Operation(summary = "Deshabilitar producto", description = "PATCH /product/{id}/disable - Deshabilita el producto indicado por {id}")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.disableProduct(id);
	}
}
