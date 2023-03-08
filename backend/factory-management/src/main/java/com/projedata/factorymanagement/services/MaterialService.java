package com.projedata.factorymanagement.services;

import com.projedata.factorymanagement.dto.MaterialDto;
import com.projedata.factorymanagement.models.Material;

import java.util.List;
import java.util.UUID;

public interface MaterialService {

    Material findById(UUID id);

    List<Material> findAll();

    Material create(MaterialDto materialDto);

    Material update(MaterialDto materialDto);

    void delete(UUID id);
}
