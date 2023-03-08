package com.projetada.factorymanagement.services.impl;

import com.projetada.factorymanagement.dto.ProductDto;
import com.projetada.factorymanagement.exceptions.DataIntegrityViolationException;
import com.projetada.factorymanagement.exceptions.ObjectNotFoundException;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.models.Product;
import com.projetada.factorymanagement.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    public static final UUID ID = UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607");
    public static final String NAME = "Machine";
    public static final Double VALUE = 24.99;
    public static final Set<Material> MATERIALS = new HashSet<>();
    public static final int INDEX = 0;
    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_NOT_FOUND = "Product Not Found";


    @InjectMocks
    private ProductServiceImpl productService;
    
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    private Product product;
    private ProductDto productDto;
    private Optional<Product> productOptional;

    private void startUsersMock() {
        product = new Product(ID, NAME, VALUE, MATERIALS);
        productDto = new ProductDto(ID, NAME, VALUE, MATERIALS);
        productOptional = Optional.of(new Product(ID, NAME, VALUE, MATERIALS));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsersMock();
    }

    @Test
    public void WhenFindByIdThenReturnSuccessResponse() {
        when(productRepository.findById(any())).thenReturn(productOptional);

        Product response = productService.findById(ID);

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(VALUE, response.getValue());
    }

    @Test
    public void WhenFindByIdThenReturnObjectNotFoundException() {
        when(productRepository.findById(any())).thenThrow(new ObjectNotFoundException(PRODUCT_NOT_FOUND));

        assertThrows(ObjectNotFoundException.class, () -> productService.findById(ID));
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class,
                () -> productService.findById(ID));
        assertEquals(PRODUCT_NOT_FOUND, objectNotFoundException.getMessage());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterials() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> response = productService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Product.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(VALUE, response.get(INDEX).getValue());
    }

    @Test
    public void WhenCreateThenReturnSuccess() {
        when(productRepository.save(any())).thenReturn(product);

        Product response = productService.create(productDto);

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(VALUE, response.getValue());
    }

    @Test
    public void WhenCreateThenReturnADataIntegrityViolationExceptionProductAlreadyExists() {
        when(productRepository.save(any())).thenThrow(new DataIntegrityViolationException(PRODUCT_ALREADY_EXISTS));

        assertThrows(DataIntegrityViolationException.class, () -> productService.create(productDto));
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class,
                () -> productService.create(productDto));
        assertEquals(PRODUCT_ALREADY_EXISTS, dataIntegrityViolationException.getMessage());

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(productRepository.findById(any())).thenReturn(productOptional);
        when(productRepository.save(any())).thenReturn(product);

        Product response = productService.update(productDto);

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(VALUE, response.getValue());
    }

    @Test
    void whenUpdateThenReturnADataIntegrityViolationExceptionProductAlreadyExists() {
        when(productRepository.findByName(anyString())).thenReturn(productOptional);

        try {
            productOptional.get().setId(UUID.fromString("f6499957-37f6-4277-9d44-c6a114512345"));
            productService.create(productDto);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(PRODUCT_ALREADY_EXISTS, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(productRepository.findById(any())).thenReturn(productOptional);
        doNothing().when(productRepository).deleteById(any());
        productService.delete(ID);
        verify(productRepository, times(1)).deleteById(any());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(productRepository.findById(any())).thenThrow(new ObjectNotFoundException(PRODUCT_NOT_FOUND));
        try {
            productService.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PRODUCT_NOT_FOUND, ex.getMessage());
        }
    }
}