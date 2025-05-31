package com.aruimari.springboot.di.app.controllers;

import com.aruimari.springboot.di.app.exceptions.ProductNotFoundException;
import com.aruimari.springboot.di.app.models.Product;
import com.aruimari.springboot.di.app.services.ProductDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    public ProductDetailsController(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @GetMapping("/{id}/similar")
    public ResponseEntity<Optional<List<Product>>> getSimilarProducts(@PathVariable Integer id) {
        Optional<List<Product>> p = Optional.ofNullable(productDetailsService.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with ID %s not found", id))));

        return ResponseEntity.status(HttpStatus.OK).body(p);

    }

}
