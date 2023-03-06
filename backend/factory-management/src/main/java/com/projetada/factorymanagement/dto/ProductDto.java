package com.projetada.factorymanagement.dto;

import com.projetada.factorymanagement.models.Material;
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
public class ProductDto {

    private UUID id;

    private String name;

    private Double value;

    private Set<Material> materials;

}
