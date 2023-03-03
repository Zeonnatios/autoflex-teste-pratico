package com.projetada.factorymanagement.controllers;

import com.projetada.factorymanagement.dto.MaterialDto;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.services.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/material")
public class MaterialController {

    private static final String ID = "/{id}";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDto> create(@RequestBody MaterialDto materialDto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID)
                .buildAndExpand(materialService.create(materialDto).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<MaterialDto>> findAll() {
        return ResponseEntity.ok().body(
                materialService.findAll().stream().map(x -> modelMapper.map(x, MaterialDto.class)).toList());
    }

    @GetMapping(value = ID)
    public ResponseEntity<MaterialDto> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(modelMapper.map(materialService.findById(id), MaterialDto.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<MaterialDto> delete(@PathVariable(value = "id") UUID id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(ID)
    public ResponseEntity<MaterialDto> update(@PathVariable(value = "id") UUID id,
                                                 @RequestBody MaterialDto materialDto) {
        materialDto.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(materialService.update(materialDto), MaterialDto.class));
    }

}

