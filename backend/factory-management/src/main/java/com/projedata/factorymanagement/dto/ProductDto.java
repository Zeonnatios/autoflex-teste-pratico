package com.projedata.factorymanagement.dto;

import com.projedata.factorymanagement.models.Recipe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProductDto {

    private UUID id;

    private String name;

    private Double value;

    private Set<Recipe> recipes;

    public ProductDto() {
    }

    public ProductDto(UUID id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public ProductDto(UUID id, String name, Double value, Set<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
