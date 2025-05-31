package com.aruimari.springboot.di.app.services;

import com.aruimari.springboot.di.app.client.ProductClient;
import com.aruimari.springboot.di.app.models.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductClient productClient;

    public ProductDetailsServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    @Cacheable(cacheNames = {"productDetailsIdsSimilar"}, key = "#productId", unless = "#result == null || #result.isEmpty()")
    public Optional<List<Product>> findById(Integer productId) {
        List<Integer> ids = productClient.getSimilarProducts(productId.toString());

        if (ids == null || ids.isEmpty()) {
            return Optional.empty();
        }

        List<Product> productList = new ArrayList<>();

        for (Integer id : ids) {
            Product product = productClient.getProductDetails(id.toString());
            productList.add(product);
        }

        return Optional.of(productList);

    }
}
