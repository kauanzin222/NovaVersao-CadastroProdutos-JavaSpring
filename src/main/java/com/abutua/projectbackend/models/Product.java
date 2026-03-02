package com.abutua.projectbackend.models;

import java.io.Serializable;

import com.abutua.projectbackend.dto.ProductResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product implements Serializable {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 1024)
    private String description;

    @Min(value = 0, message = "Price min value = 0")
    private Double price;

    private boolean promotion;
    private boolean newProduct;

    @ManyToOne
    @Valid
    private Category category;

    // Constructor JPA
    public Product() {
    }

    // Métodos Construtores
    public Product(String name, String desc, Category category, boolean prom, boolean newProd, Double price) {
        this.name = name;
        this.description = desc;
        this.category = category;
        this.promotion = prom;
        this.newProduct = newProd;
        this.price = price;
    }

    public ProductResponse toDTO() {
        return new ProductResponse(id, name, description, price, promotion, newProduct, category);
    }

    // Métodos de Produto
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    // Boas Práticas
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (promotion ? 1231 : 1237);
        result = prime * result + (newProduct ? 1231 : 1237);
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (promotion != other.promotion)
            return false;
        if (newProduct != other.newProduct)
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
                + ", promotion=" + promotion + ", newProduct=" + newProduct + "]";
    }

}
