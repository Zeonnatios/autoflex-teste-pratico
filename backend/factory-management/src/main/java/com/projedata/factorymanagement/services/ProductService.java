package com.projedata.factorymanagement.services;

import com.projedata.factorymanagement.dto.ProductDto;
import com.projedata.factorymanagement.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product findById(UUID id);

    List<Product> findAll();

    Product create(ProductDto productDto);

    Product update(ProductDto productDto);

    void delete(UUID id);

}
