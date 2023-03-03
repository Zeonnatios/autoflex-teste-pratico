package com.projetada.factorymanagement.services;

import com.projetada.factorymanagement.dto.MaterialDto;
import com.projetada.factorymanagement.dto.ProductDto;
import com.projetada.factorymanagement.models.Material;
import com.projetada.factorymanagement.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product findById(UUID id);

    List<Product> findAll();

    Product create(ProductDto productDto);

    Product update(ProductDto productDto);

    void delete(UUID id);

}
