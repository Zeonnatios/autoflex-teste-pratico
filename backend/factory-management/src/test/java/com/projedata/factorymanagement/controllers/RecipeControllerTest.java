package com.projedata.factorymanagement.controllers;

import com.projedata.factorymanagement.FactoryManagementApplication;
import com.projedata.factorymanagement.config.ContainersEnvironment;
import com.projedata.factorymanagement.dto.MaterialDto;
import com.projedata.factorymanagement.dto.RecipeDto;
import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.models.Recipe;
import com.projedata.factorymanagement.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeControllerTest extends ContainersEnvironment {

    public static final UUID ID = UUID.fromString("a509b0ef-8d08-426d-93fe-b96f7cd35090");
    public static final UUID PRODUCT_ID = UUID.fromString("a87eb5b5-ce22-48c2-90be-0f0ec38b79f5");
    public static final UUID MATERIAL_ID = UUID.fromString("13bc64e5-d8c7-4a77-9616-b95f6755fc22");
    public static final Integer QUANTITY = 20;
    public static final int INDEX = 0;


    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

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
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @Test
    public void WhenFindByIdThenReturnSuccess() {
        when(recipeService.findById(any())).thenReturn(recipe);
        when(modelMapper.map(any(), any())).thenReturn(recipeDto);

        ResponseEntity<RecipeDto> response = recipeController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(RecipeDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(QUANTITY, response.getBody().getQuantity());
        assertEquals(PRODUCT_ID, response.getBody().getProduct().getId());
        assertEquals(MATERIAL_ID, response.getBody().getMaterial().getId());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterialDto() {
        when(recipeService.findAll()).thenReturn(List.of(recipe));
        when(modelMapper.map(any(), any())).thenReturn(recipeDto);

        ResponseEntity<List<RecipeDto>> response = recipeController.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(RecipeDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(QUANTITY, response.getBody().get(INDEX).getQuantity());
        assertEquals(PRODUCT_ID, response.getBody().get(INDEX).getProduct().getId());
        assertEquals(MATERIAL_ID, response.getBody().get(INDEX).getMaterial().getId());


    }

    @Test
    void whenCreateThenReturnCreated() {
        when(recipeService.create(any())).thenReturn(recipe);

        ResponseEntity<RecipeDto> response = recipeController.create(recipeDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(recipeService.update(recipeDto)).thenReturn(recipe);
        when(modelMapper.map(any(), any())).thenReturn(recipeDto);

        ResponseEntity<RecipeDto> response = recipeController.update(ID, recipeDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(RecipeDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(QUANTITY, response.getBody().getQuantity());
        assertEquals(PRODUCT_ID, response.getBody().getProduct().getId());
        assertEquals(MATERIAL_ID, response.getBody().getMaterial().getId());
    }
}