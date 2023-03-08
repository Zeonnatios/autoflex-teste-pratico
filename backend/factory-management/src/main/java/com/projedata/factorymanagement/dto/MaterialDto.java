package com.projedata.factorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projedata.factorymanagement.models.Recipe;

import java.util.Set;
import java.util.UUID;

public class MaterialDto {

    private UUID id;

    private String name;

    private Integer stock;

    private Set<Recipe> recipes;

    public MaterialDto() {
    }

    public MaterialDto(UUID id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public MaterialDto(UUID id, String name, Integer stock, Set<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.recipes = recipes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
