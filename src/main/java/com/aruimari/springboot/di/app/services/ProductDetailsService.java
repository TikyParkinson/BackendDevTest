package com.aruimari.springboot.di.app.services;

import com.aruimari.springboot.di.app.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDetailsService {

    Optional<List<Product>> findById(Integer id);
}
