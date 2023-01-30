package com.projetada.factorymanagement.controllers;

import com.projetada.factorymanagement.dtos.MaterialDto;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @PostMapping
    public ResponseEntity<Object> saveMaterial(@RequestBody Material material) {
        if (materialService.existsByName(material.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Material already exists!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.save(material));
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        return ResponseEntity.status(HttpStatus.OK).body(materialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMaterial(@PathVariable(value = "id") UUID id) {
        Optional<Material> materialOptional = materialService.findById(id);
        if (materialOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found !");
        }
        return ResponseEntity.status(HttpStatus.OK).body(materialOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMaterial(@PathVariable(value = "id") UUID id) {
        Optional<Material> materialOptional = materialService.findById(id);
        if (materialOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found !");
        }
        materialService.delete(materialOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Material deleted successfully !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMaterial(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid MaterialDto materialDto) {
        Optional<Material> materialOptional = materialService.findById(id);
        if (materialOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found !");
        }
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        material.setId(materialOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(materialService.save(material));
    }

}

