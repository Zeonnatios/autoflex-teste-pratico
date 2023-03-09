package com.projedata.factorymanagement.services.impl;

import com.projedata.factorymanagement.FactoryManagementApplication;
import com.projedata.factorymanagement.config.ContainersEnvironment;
import com.projedata.factorymanagement.dto.RecipeDto;
import com.projedata.factorymanagement.exceptions.ObjectNotFoundException;
import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.models.Recipe;
import com.projedata.factorymanagement.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeServiceImplTest extends ContainersEnvironment {

    public static final UUID ID = UUID.fromString("a509b0ef-8d08-426d-93fe-b96f7cd35090");
    public static final UUID PRODUCT_ID = UUID.fromString("a87eb5b5-ce22-48c2-90be-0f0ec38b79f5");
    public static final UUID MATERIAL_ID = UUID.fromString("13bc64e5-d8c7-4a77-9616-b95f6755fc22");
    public static final Integer QUANTITY = 20;
    public static final int INDEX = 0;
    public static final String RECIPE_NOT_FOUND = "Recipe Not Found";

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ModelMapper modelMapper;

    private Recipe recipe;
    private RecipeDto recipeDto;
    private Optional<Recipe> recipeOptional;

    private Material material;
    private Product product;

    private void startUsersMock() {
        material = new Material(MATERIAL_ID, "Plastic", 50);
        product = new Product(PRODUCT_ID, "Machine", 124.99);
        recipe = new Recipe(ID, product, material, QUANTITY);
        recipeDto = new RecipeDto(ID, product, material, QUANTITY);
        recipeOptional = Optional.of(new Recipe(ID, product, material, QUANTITY));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsersMock();
    }

    @Test
    public void WhenFindByIdThenReturnSuccessResponse() {
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);

        Recipe response = recipeService.findById(ID);

        assertNotNull(response);
        assertEquals(Recipe.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(QUANTITY, response.getQuantity());
        assertEquals(PRODUCT_ID, response.getProduct().getId());
        assertEquals(MATERIAL_ID, response.getMaterial().getId());
    }

    @Test
    public void WhenFindByIdThenReturnObjectNotFoundException() {
        when(recipeRepository.findById(any())).thenThrow(new ObjectNotFoundException(RECIPE_NOT_FOUND));

        assertThrows(ObjectNotFoundException.class, () -> recipeService.findById(ID));
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class,
                () -> recipeService.findById(ID));
        assertEquals(RECIPE_NOT_FOUND, objectNotFoundException.getMessage());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterials() {
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<Recipe> response = recipeService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Recipe.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(QUANTITY, response.get(INDEX).getQuantity());
        assertEquals(PRODUCT_ID, response.get(INDEX).getProduct().getId());
        assertEquals(MATERIAL_ID, response.get(INDEX).getMaterial().getId());
    }

    @Test
    public void WhenCreateThenReturnSuccess() {
        when(recipeRepository.save(any())).thenReturn(recipe);

        Recipe response = recipeService.create(recipeDto);

        assertNotNull(response);
        assertEquals(Recipe.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(QUANTITY, response.getQuantity());
        assertEquals(product, response.getProduct());
        assertEquals(material, response.getMaterial());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(recipe);

        Recipe response = recipeService.update(recipeDto);

        assertNotNull(response);
        assertEquals(Recipe.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(QUANTITY, response.getQuantity());
        assertEquals(PRODUCT_ID, response.getProduct().getId());
        assertEquals(MATERIAL_ID, response.getMaterial().getId());
    }

}