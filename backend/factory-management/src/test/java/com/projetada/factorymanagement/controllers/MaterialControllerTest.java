package com.projetada.factorymanagement.controllers;

import com.projetada.factorymanagement.FactoryManagementApplication;
import com.projetada.factorymanagement.config.ContainersEnvironment;
import com.projetada.factorymanagement.dto.MaterialDto;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.models.Product;
import com.projetada.factorymanagement.services.MaterialService;
import com.projetada.factorymanagement.services.impl.MaterialServiceImpl;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MaterialControllerTest extends ContainersEnvironment {

    public static final UUID ID = UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607");
    public static final String NAME = "Plastic";
    public static final Integer STOCK = 20;
    public static final Set<Product> PRODUCTS = new HashSet<>();
    public static final int INDEX = 0;
    public static final String MATERIAL_ALREADY_EXISTS = "Material already exists";
    public static final String MATERIAL_NOT_FOUND = "Material Not Found";

    @InjectMocks
    private MaterialController materialController;

    @Mock
    private MaterialService materialService;

    @Mock
    private ModelMapper modelMapper;

    private Material material;
    private MaterialDto materialDto;
    private Optional<Material> optionalMaterial;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsersMock();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    private void startUsersMock() {
        material = new Material(ID, NAME, STOCK, PRODUCTS);
        materialDto = new MaterialDto(ID, NAME, STOCK, PRODUCTS);
        optionalMaterial = Optional.of(new Material(ID, NAME, STOCK, PRODUCTS));
    }

    @Test
    public void WhenFindByIdThenReturnSuccess() {
        when(materialService.findById(any())).thenReturn(material);
        when(modelMapper.map(any(), any())).thenReturn(materialDto);

        ResponseEntity<MaterialDto> response = materialController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(MaterialDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(STOCK, response.getBody().getStock());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterialDto() {
        when(materialService.findAll()).thenReturn(List.of(material));
        when(modelMapper.map(any(), any())).thenReturn(materialDto);

        ResponseEntity<List<MaterialDto>> response = materialController.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(MaterialDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(STOCK, response.getBody().get(INDEX).getStock());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(materialService.create(any())).thenReturn(material);

        ResponseEntity<MaterialDto> response = materialController.create(materialDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(materialService.update(materialDto)).thenReturn(material);
        when(modelMapper.map(any(), any())).thenReturn(materialDto);

        ResponseEntity<MaterialDto> response = materialController.update(ID, materialDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(MaterialDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(STOCK, response.getBody().getStock());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(materialService).delete(any());

        ResponseEntity<MaterialDto> response = materialController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(materialService, times(1)).delete(any());
    }
}