package com.abutua.projectbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.projectbackend.dto.ProductRequest;
import com.abutua.projectbackend.dto.ProductResponse;
import com.abutua.projectbackend.models.Product;
import com.abutua.projectbackend.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductController {

        @Autowired
        public ProductService productService;

        @PostMapping
        public ResponseEntity<ProductResponse> save(@Validated @RequestBody ProductRequest productRequest) {
                ProductResponse productResponse = productService.save(productRequest);

                URI location = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(productResponse.getId())
                                .toUri();

                return ResponseEntity.created(location).body(productResponse);
        }

        @GetMapping("{id}")
        public ResponseEntity<Product> getProduct(@PathVariable long id) {
                Product product = productService.getById(id);
                return ResponseEntity.ok(product);
        }

        @GetMapping
        public ResponseEntity<List<Product>> getProducts() {
                return ResponseEntity.ok(productService.getAll());
        }

        @DeleteMapping("{id}")
        public ResponseEntity<Void> removeProduct(@PathVariable long id) {
                productService.deleteById(id);
                return ResponseEntity.noContent().build();
        }

        @PutMapping("{id}")
        public ResponseEntity<Void> updateProduct(@PathVariable long id, @RequestBody Product productUpdate) {
                productService.update(id, productUpdate);
                return ResponseEntity.ok().build();
        }

}
