package com.projedata.factorymanagement.services.impl;


import com.projedata.factorymanagement.FactoryManagementApplication;
import com.projedata.factorymanagement.dto.MaterialDto;
import com.projedata.factorymanagement.exceptions.DataIntegrityViolationException;
import com.projedata.factorymanagement.exceptions.ObjectNotFoundException;
import com.projedata.factorymanagement.models.Material;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.models.Recipe;
import com.projedata.factorymanagement.repositories.MaterialRepository;
import com.projedata.factorymanagement.config.ContainersEnvironment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MaterialServiceImplTest extends ContainersEnvironment {

    public static final UUID ID = UUID.fromString("f6499957-37f6-4277-9d44-c6a114531607");
    public static final String NAME = "Plastic";
    public static final Integer STOCK = 20;
    public static final Set<Recipe> RECIPES = new HashSet<>();
    public static final int INDEX = 0;
    public static final String MATERIAL_ALREADY_EXISTS = "Material already exists";
    public static final String MATERIAL_NOT_FOUND = "Material Not Found";

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
        startUsersMock();
    }

    private void startUsersMock() {
        material = new Material(ID, NAME, STOCK);
        materialDto = new MaterialDto(ID, NAME, STOCK);
        optionalMaterial = Optional.of(new Material(ID, NAME, STOCK));
    }

    @Test
    public void WhenFindByIdThenReturnSuccessResponse() {
        when(materialRepository.findById(any())).thenReturn(optionalMaterial);

        Material response = materialService.findById(ID);

        assertNotNull(response);
        assertEquals(Material.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(STOCK, response.getStock());
    }

    @Test
    public void WhenFindByIdThenReturnObjectNotFoundException() {
        when(materialRepository.findById(any())).thenThrow(new ObjectNotFoundException(MATERIAL_NOT_FOUND));

        assertThrows(ObjectNotFoundException.class, () -> materialService.findById(ID));
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class,
                () -> materialService.findById(ID));
        assertEquals(MATERIAL_NOT_FOUND, objectNotFoundException.getMessage());
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
    public void WhenCreateThenReturnSuccess() {
        when(materialRepository.save(any())).thenReturn(material);

        Material response = materialService.create(materialDto);

        assertNotNull(response);
        assertEquals(Material.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(STOCK, response.getStock());
    }

    @Test
    public void WhenCreateThenReturnADataIntegrityViolationExceptionMaterialAlreadyExists() {
        when(materialRepository.save(any())).thenThrow(new DataIntegrityViolationException(MATERIAL_ALREADY_EXISTS));

        assertThrows(DataIntegrityViolationException.class, () -> materialService.create(materialDto));
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class,
                () -> materialService.create(materialDto));
        assertEquals(MATERIAL_ALREADY_EXISTS, dataIntegrityViolationException.getMessage());

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(materialRepository.findById(any())).thenReturn(optionalMaterial);
        when(materialRepository.save(any())).thenReturn(material);

        Material response = materialService.update(materialDto);

        assertNotNull(response);
        assertEquals(Material.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(STOCK, response.getStock());
    }

    @Test
    void whenUpdateThenReturnADataIntegrityViolationExceptionMaterialAlreadyExists() {
        when(materialRepository.findByName(anyString())).thenReturn(optionalMaterial);

        try {
            optionalMaterial.get().setId(UUID.fromString("f6499957-37f6-4277-9d44-c6a114512345"));
            materialService.create(materialDto);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(MATERIAL_ALREADY_EXISTS, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(materialRepository.findById(any())).thenReturn(optionalMaterial);
        doNothing().when(materialRepository).deleteById(any());
        materialService.delete(ID);
        verify(materialRepository, times(1)).deleteById(any());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(materialRepository.findById(any())).thenThrow(new ObjectNotFoundException(MATERIAL_NOT_FOUND));
        try {
            materialService.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(MATERIAL_NOT_FOUND, ex.getMessage());
        }
    }
}