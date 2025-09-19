package com.product.api.controller;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase CtrlCategory que actúa como controlador 
 */
@RestController
@RequestMapping("/category")
public class CtrlCategory {
    @Autowired
    SvcCategory svc;
    
    /**
     * Método para obtener las categorías
     * @return Lista de categorías
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return svc.getCategories();
    }
}