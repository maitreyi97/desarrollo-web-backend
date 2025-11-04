package com.product.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

/**
 * Interfaz RepoCategory que extiende JpaRepository
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category ORDER BY category_id", nativeQuery = true)
    /**
     * Método para obtener las categorías
     * 
     * @return Lista de categorías
     */
    List<Category> findAll();

    @Query(value = "SELECT * FROM category WHERE status = 1 ORDER BY category_id", nativeQuery = true)
    List<Category> findActive();

    @Query(value = "SELECT * FROM category WHERE category = :category", nativeQuery = true)
    Category findByCategory(@Param("category") String category);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
    void create(@Param("category") String category, @Param("tag") String tag);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :id", nativeQuery = true)
    void update(@Param("category") String category, @Param("tag") String tag, @Param("id") Integer id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE category SET status = 1 WHERE category_id = :id", nativeQuery = true)
    void enable(@Param("id") Integer id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE category SET status = 0 WHERE category_id = :id", nativeQuery = true)
    void disable(@Param("id") Integer id);
}