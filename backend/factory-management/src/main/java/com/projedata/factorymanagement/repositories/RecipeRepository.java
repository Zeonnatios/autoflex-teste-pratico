package com.projedata.factorymanagement.repositories;

import com.projedata.factorymanagement.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}
