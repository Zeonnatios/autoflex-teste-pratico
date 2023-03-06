package com.projetada.factorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.projetada.factorymanagement.models.Product;
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
    private Set<Product> products;

}
