package com.aruimari.springboot.di.app.controllers;


import com.aruimari.springboot.di.app.client.ProductClient;
import com.aruimari.springboot.di.app.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductDetailsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductClient productClient;

    @Test
    void testGetProductDetails() throws Exception {
        String productId = "1";
        List<Integer> ids = Arrays.asList(2, 3);

        Product product1 = new Product(2L, "Logitech G933", 79.99, true);
        Product product2 = new Product(3L, "Intel Core 5 2500k", 399.99, true);

        when(productClient.getSimilarProducts(productId)).thenReturn(ids);
        when(productClient.getProductDetails("2")).thenReturn(product1);
        when(productClient.getProductDetails("3")).thenReturn(product2);

        mockMvc.perform(get("/product/1/similar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("2"))
                .andExpect(jsonPath("$[1].id").value("3"));
    }
}
