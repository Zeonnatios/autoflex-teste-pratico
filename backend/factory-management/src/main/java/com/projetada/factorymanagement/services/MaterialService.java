package com.projetada.factorymanagement.services;

import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Transactional
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public boolean existsByName(String name) {
        return materialRepository.existsByName(name);
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(UUID id) {
        return materialRepository.findById(id);
    }

    @Transactional
    public void delete(Material material) {
        materialRepository.delete(material);
    }
}
