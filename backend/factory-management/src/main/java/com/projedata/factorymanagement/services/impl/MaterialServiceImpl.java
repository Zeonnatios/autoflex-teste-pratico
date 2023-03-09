package com.projedata.factorymanagement.services.impl;

import com.projedata.factorymanagement.dto.MaterialDto;
import com.projedata.factorymanagement.exceptions.DataIntegrityViolationException;
import com.projedata.factorymanagement.exceptions.ObjectNotFoundException;
import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.repositories.MaterialRepository;
import com.projedata.factorymanagement.services.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Material findById(UUID id) {
        Optional<Material> materialOptional = materialRepository.findById(id);
        return materialOptional.orElseThrow(() -> new ObjectNotFoundException("Material Not Found"));
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material create(MaterialDto materialDto) {
        findByName(materialDto);
        return materialRepository.save(modelMapper.map(materialDto, Material.class));
    }

    @Override
    public Material update(MaterialDto materialDto) {
        findById(materialDto.getId());
        findByName(materialDto);
        return materialRepository.save(modelMapper.map(materialDto, Material.class));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        materialRepository.deleteById(id);
    }

    private void findByName(MaterialDto materialDto) {
        Optional<Material> materialOptional = materialRepository.findByName(materialDto.getName());
        if (materialOptional.isPresent() && !materialOptional.get().getId().equals(materialDto.getId())) {
            throw new DataIntegrityViolationException("Material already exists");
        }
    }

}
