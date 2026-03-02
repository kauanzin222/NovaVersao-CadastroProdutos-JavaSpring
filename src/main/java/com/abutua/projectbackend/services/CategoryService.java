package com.abutua.projectbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.projectbackend.dto.CategoryRequest;
import com.abutua.projectbackend.dto.CategoryResponse;
import com.abutua.projectbackend.models.Category;
import com.abutua.projectbackend.models.Product;
import com.abutua.projectbackend.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public Category getByProduct(Product productUpdate) {
        Category category = categoryRepository.findById(productUpdate.getCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
        return category;
    }

    public CategoryResponse getDTOById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
        return category.toDTO();
    }

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
        return category;
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(c -> c.toDTO()).collect(Collectors.toList());
    }

    public void update(int id, CategoryRequest categoryUpdate) {
        Category category = getById(id);
        category.setName(categoryUpdate.getName());

        categoryRepository.save(category);
    }

    public CategoryResponse save(CategoryRequest categoryRequest) { 
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    public void deleteById(int id) {
        categoryRepository.delete(getById(id));
    }
}
