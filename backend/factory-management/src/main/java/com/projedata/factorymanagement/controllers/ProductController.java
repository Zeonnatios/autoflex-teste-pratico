package com.projedata.factorymanagement.controllers;

import com.projedata.factorymanagement.models.Product;
import com.projedata.factorymanagement.services.ProductService;
import com.projedata.factorymanagement.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    private static final String ID = "/{id}";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        Product product = productService.create(productDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID)
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(product, ProductDto.class));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok().body(
                productService.findAll().stream().map(x -> modelMapper.map(x, ProductDto.class)).toList());
    }

    @GetMapping(value = ID)
    public ResponseEntity<ProductDto> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(modelMapper.map(productService.findById(id), ProductDto.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<ProductDto> delete(@PathVariable(value = "id") UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<ProductDto> update(@PathVariable(value = "id") UUID id,
                                             @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(productService.update(productDto), ProductDto.class));
    }

}
