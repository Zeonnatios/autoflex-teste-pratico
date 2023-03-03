package com.projetada.factorymanagement.services;

import com.projetada.factorymanagement.dto.MaterialDto;
import com.projetada.factorymanagement.models.Material;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialService {

    Material findById(UUID id);

    List<Material> findAll();

    Material create(MaterialDto materialDto);

    Material update(MaterialDto materialDto);

    void delete(UUID id);
}
