package com.product.api.entity;
import jakarta.persistence.*;

/**
 * Clase Category que representa una categoría de un producto
 */

@Entity
@Table(name="category")
public class Category {
    @Id
    private Integer category_id;
    private String category;
    private String tag;
    private Integer status;

    /**
     * Constructor de la clase Category
     * @param id el id de la categoría
     * @param category el nombre de la categoría
     * @param tag la etiqueta de la categoría
     * @param status el estado de la categoría
     */
    public Category(Integer category_id, String category, String tag, Integer status) {
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    public Category() {
    }

    //Getters y setters de la clase Category
    
    public Integer getCategory_Id() {
        return category_id;
    }

    public void setCategory_Id(Integer id) {
        this.category_id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" +category_id +", " +category +", " +tag +", " +status+ "]";
    }
}