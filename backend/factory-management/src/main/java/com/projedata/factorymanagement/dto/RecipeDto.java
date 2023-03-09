package com.projedata.factorymanagement.dto;

import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private UUID id;

    private Product product;

    private Material material;

    private Integer quantity;
}
