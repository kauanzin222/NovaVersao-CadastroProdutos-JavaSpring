package com.abutua.projectbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.projectbackend.dto.ProductRequest;
import com.abutua.projectbackend.dto.ProductResponse;
import com.abutua.projectbackend.models.Category;
import com.abutua.projectbackend.models.Product;
import com.abutua.projectbackend.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public CategoryService categoryService;

    public Product getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        return product;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.delete(getById(id));
    }

    public void update(Long id, Product productUpdate) {
        Product product = getById(id);

        if (productUpdate.getCategory() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can not be empty");

        Category category = categoryService.getByProduct(productUpdate);

        product.setDescription(productUpdate.getDescription());
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setNewProduct(productUpdate.isNewProduct());
        product.setPromotion(productUpdate.isPromotion());
        product.setCategory(category);

        productRepository.save(product);
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toEntity());
        return product.toDTO();
    }
}
