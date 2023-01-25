package com.projetada.factorymanagement.services;

import com.projetada.factorymanagement.models.Product;
import com.projetada.factorymanagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
