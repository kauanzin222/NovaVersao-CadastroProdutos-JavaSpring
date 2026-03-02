package com.abutua.projectbackend.dto;

import com.abutua.projectbackend.models.Category;
import com.abutua.projectbackend.models.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequest {
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name lenght min = 3 and max = 255")
    private String name;

    @NotBlank(message = "Description can not be blank")
    @Size(min = 3, max = 1024, message = "Description lenght min = 3 and max = 1024")
    private String description;

    @Min(value = 0, message = "Price min value = 0")
    private Double price;

    private boolean promotion;
    private boolean newProduct;

    private IntegerDTO category;

    public Product toEntity() {
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setNewProduct(newProduct);
        product.setPromotion(promotion);
        product.setCategory(new Category(category.getId()));

        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public IntegerDTO getCategory() {
        return category;
    }

    public void setCategory(IntegerDTO category) {
        this.category = category;
    }

}
