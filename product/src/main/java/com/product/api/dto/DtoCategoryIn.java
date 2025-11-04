package com.product.api.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoCategoryIn {
    @JsonProperty("category")
    @NotNull(message = "La categoria es obligatoria")
    private String category;

    @JsonProperty("tag")
    @NotNull(message = "El tag es obligatorio")
    private String tag;

    public DtoCategoryIn() {
    }

    public DtoCategoryIn(String category, String tag) {
        this.category = category;
        this.tag = tag;
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
}
