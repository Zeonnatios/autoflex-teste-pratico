package com.projedata.factorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.models.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private UUID id;

    private String name;

    private Integer stock;

    @JsonIgnore
    private Set<Recipe> recipes;

    public MaterialDto(UUID id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
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
