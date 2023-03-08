package com.projedata.factorymanagement.services.impl;

import com.projedata.factorymanagement.exceptions.ObjectNotFoundException;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.models.Recipe;
import com.projedata.factorymanagement.repositories.RecipeRepository;
import com.projedata.factorymanagement.services.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> findById(UUID id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        return Optional.ofNullable(recipeOptional.orElseThrow(() -> new ObjectNotFoundException("Product Not Found")));
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
