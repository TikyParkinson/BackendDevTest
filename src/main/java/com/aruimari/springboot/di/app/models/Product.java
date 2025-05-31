package com.aruimari.springboot.di.app.models;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private boolean isAvaliability;

    public Product() {}

    public Product(Long id, String name, Double price, boolean isAvaliability) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvaliability = isAvaliability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public boolean getAvaliability() {
        return isAvaliability;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvaliability(boolean isAvaliability) {
        this.isAvaliability = isAvaliability;
    }


}
