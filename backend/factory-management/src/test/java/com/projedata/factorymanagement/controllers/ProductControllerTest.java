package com.projedata.factorymanagement.controllers;

import com.projedata.factorymanagement.FactoryManagementApplication;
import com.projedata.factorymanagement.config.ContainersEnvironment;
import com.projedata.factorymanagement.dto.ProductDto;
import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.services.ProductService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest extends ContainersEnvironment {

    public static final UUID ID = UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607");
    public static final String NAME = "Machine";
    public static final Double VALUE = 24.99;
    public static final Set<Material> MATERIALS = new HashSet<>();
    public static final int INDEX = 0;
    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_NOT_FOUND = "Product Not Found";

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ModelMapper modelMapper;

    private Product product;
    private ProductDto productDto;
    private Optional<Product> productOptional;

    private void startUsersMock() {
        MATERIALS.add(new Material(UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607"), "Plastic", 24, new HashSet<>()));
        product = new Product(ID, NAME, VALUE, MATERIALS);
        productDto = new ProductDto(ID, NAME, VALUE, MATERIALS);
        productOptional = Optional.of(new Product(ID, NAME, VALUE, MATERIALS));
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
        when(productService.findById(any())).thenReturn(product);
        when(modelMapper.map(any(), any())).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ProductDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        assertEquals(MATERIALS, response.getBody().getMaterials());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterialDto() {
        when(productService.findAll()).thenReturn(List.of(product));
        when(modelMapper.map(any(), any())).thenReturn(productDto);

        ResponseEntity<List<ProductDto>> response = productController.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ProductDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(VALUE, response.getBody().get(INDEX).getValue());
        assertEquals(MATERIALS, response.getBody().get(INDEX).getMaterials());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(productService.create(any())).thenReturn(product);

        ResponseEntity<ProductDto> response = productController.create(productDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(productService.update(productDto)).thenReturn(product);
        when(modelMapper.map(any(), any())).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.update(ID, productDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ProductDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        assertEquals(MATERIALS, response.getBody().getMaterials());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(productService).delete(any());

        ResponseEntity<ProductDto> response = productController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).delete(any());
    }

}