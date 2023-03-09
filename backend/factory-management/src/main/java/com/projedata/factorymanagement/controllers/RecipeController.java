package com.projedata.factorymanagement.controllers;

import com.projedata.factorymanagement.dto.ProductDto;
import com.projedata.factorymanagement.dto.RecipeDto;
import com.projedata.factorymanagement.models.Recipe;
import com.projedata.factorymanagement.services.ProductService;
import com.projedata.factorymanagement.services.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/recipe")
public class RecipeController {

    private static final String ID = "/{id}";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDto> create(@RequestBody RecipeDto recipeDto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID)
                .buildAndExpand(recipeService.create(recipeDto).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> findAll() {

        return ResponseEntity.ok().body(
                recipeService.findAll().stream().map(x -> modelMapper.map(x, RecipeDto.class)).toList());
    }

    @GetMapping(value = ID)
    public ResponseEntity<RecipeDto> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(modelMapper.map(recipeService.findById(id), RecipeDto.class));
    }

    @PutMapping(value = ID)
    public ResponseEntity<RecipeDto> update(@PathVariable(value = "id") UUID id,
                                            @RequestBody RecipeDto recipeDto) {
        recipeDto.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(recipeService.update(recipeDto), RecipeDto.class));
    }

}
