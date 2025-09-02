package com.product;

public class Category {
    private Integer id;
    private String category;
    private String tag;
    private Integer status;

    public Category(Integer id, String category, String tag, Integer status){
        this.id = id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    public Integer getId(){ 
        return id; 
    }

    public void setId(Integer id){ 
        this.id = id; 
    }

    public String getCategory(){ 
        return category; 
    }

    public void setCategory(String category){ 
        this.category = category; 
    }

    public String getTag(){ 
        return tag; 
    }

    public void setTag(String tag){ 
        this.tag = tag; 
    }

    public Integer getStatus(){ 
        return status; 
    }

    public void setStatus(Integer status){ 
        this.status = status; 
    }
}
