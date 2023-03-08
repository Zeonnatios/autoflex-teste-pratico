package com.projedata.factorymanagement.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Double value;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new HashSet<>();

    public Product() {
    }


    public Product(UUID id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.recipes = new HashSet<>();
    }

    public Product(UUID id, String name, Double value, Set<Recipe> recipes) {
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
