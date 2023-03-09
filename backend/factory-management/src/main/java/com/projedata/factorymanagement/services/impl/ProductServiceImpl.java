package com.projedata.factorymanagement.services.impl;

import com.projedata.factorymanagement.dto.ProductDto;
import com.projedata.factorymanagement.exceptions.DataIntegrityViolationException;
import com.projedata.factorymanagement.exceptions.ObjectNotFoundException;
import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.repositories.ProductRepository;
import com.projedata.factorymanagement.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product findById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() -> new ObjectNotFoundException("Product Not Found"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductDto productDto) {
        findByName(productDto);
        return productRepository.save(modelMapper.map(productDto, Product.class));
    }

    @Override
    public Product update(ProductDto productDto) {
        findById(productDto.getId());
        findByName(productDto);
        return productRepository.save(modelMapper.map(productDto, Product.class));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        productRepository.deleteById(id);
    }

    private void findByName(ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findByName(productDto.getName());
        if (productOptional.isPresent() && !productOptional.get().getId().equals(productDto.getId())) {
            throw new DataIntegrityViolationException("Product already exists");
        }
    }
}
