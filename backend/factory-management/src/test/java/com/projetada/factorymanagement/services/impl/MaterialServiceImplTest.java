package com.projetada.factorymanagement.services.impl;


import com.projetada.factorymanagement.dto.MaterialDto;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.models.Product;
import com.projetada.factorymanagement.repositories.MaterialRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class MaterialServiceImplTest {

    public static final UUID ID = UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607");
    public static final String NAME = "Glass";
    public static final Integer STOCK = 20;
    public static final Set<Product> PRODUCTS = new HashSet<>();
    public static final int INDEX = 0;
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String USER_NOT_FOUND = "User Not Found";

    @InjectMocks
    private MaterialServiceImpl materialService;

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ModelMapper modelMapper;

    private Material material;
    private MaterialDto materialDto;
    private Optional<Material> optionalMaterial;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void startUsersMock() {
        material = new Material(ID, NAME, STOCK, PRODUCTS);
        materialDto = new MaterialDto(ID, NAME, STOCK, PRODUCTS);
        optionalMaterial = Optional.of(new Material(ID, NAME, STOCK, PRODUCTS));
    }

    @Test
    public void WhenFindByIdThenReturnSuccessResponse() {
        when(materialRepository.findById(any(UUID.class))).thenReturn(optionalMaterial);

        Material response = materialService.findById(ID);

        assertNotNull(response);
        assertEquals(Material.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(STOCK, response.getStock());
    }

    @Test
    public void WhenFindAllThenReturnAListOfMaterials() {
        when(materialRepository.findAll()).thenReturn(List.of(material));

        List<Material> response = materialService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Material.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(STOCK, response.get(INDEX).getStock());
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}