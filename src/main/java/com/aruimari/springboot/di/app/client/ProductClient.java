package com.aruimari.springboot.di.app.client;

import com.aruimari.springboot.di.app.exceptions.ProductNotFoundException;
import com.aruimari.springboot.di.app.exceptions.ServerErrorException;
import com.aruimari.springboot.di.app.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ProductClient {

    @Value("${external.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Integer> getSimilarProducts(String productId) {
        String url = String.format("%s/product/%s/similarids", apiUrl, productId);

        try {
            Integer[] productSimilarId = restTemplate.getForObject(url, Integer[].class);
            return Arrays.asList(Objects.requireNonNull(productSimilarId));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException(String.format("Product not found for ID: %s", productId));
            }
            throw new ServerErrorException(String.format("Client error while fetching similar products for ID %s: %s", productId, e.getMessage()));
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(String.format("Server error while fetching similar products for ID %s: %s", productId, e.getMessage()));
        } catch (RestClientException e) {
            throw new ServerErrorException(String.format("Unexpected error while fetching similar products for ID %s: %s", productId, e.getMessage()));
        }
    }

    @Cacheable(cacheNames = "productDetails", key = "#productId", unless = "#result == null")
    public Product getProductDetails(String productId) {
        String url = String.format("%s/product/%s", apiUrl, productId);

        try {
            return restTemplate.getForObject(url, Product.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException(String.format("Product not found with ID: %s", productId));
            }
            throw new ServerErrorException(String.format("Client error while fetching product details for ID %s: %s", productId, e.getMessage()));
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(String.format("Server error while fetching product details for ID %s: %s", productId, e.getMessage()));
        } catch (RestClientException e) {
            throw new ServerErrorException(String.format("Unexpected error while fetching product details for ID %s: %s", productId, e.getMessage()));
        }
    }
}
