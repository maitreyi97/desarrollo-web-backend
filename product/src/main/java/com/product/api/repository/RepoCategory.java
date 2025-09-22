package com.product.api.repository;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.product.api.entity.Category;

/**
 * Interfaz RepoCategory que extiende JpaRepository 
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

    @Query(value ="SELECT * FROM category ORDER BY category_id", nativeQuery=true)
    /**
     * Método para obtener las categorías
     * @return Lista de categorías
     */
    List<Category> getCategories();
}