package com.projedata.factorymanagement.services;

import com.projedata.factorymanagement.dto.RecipeDto;
import com.projedata.factorymanagement.models.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {

    Recipe create(RecipeDto recipeDto);

    Recipe findById(UUID id);

    List<Recipe> findAll();

    Recipe update(RecipeDto recipeDto);
}
