package com.projedata.factorymanagement.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private UUID id;

    private String name;

    private Integer stock;

}
