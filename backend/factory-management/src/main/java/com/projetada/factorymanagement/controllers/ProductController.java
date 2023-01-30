package com.projetada.factorymanagement.controllers;

import com.projetada.factorymanagement.dtos.ProductDto;
import com.projetada.factorymanagement.models.Product;
import com.projetada.factorymanagement.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        if (productService.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product already exists!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found !");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found !");
        }
        productService.delete(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductDto productDto) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found !");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(productOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

}
