package com.quangduong.elasticsearch.controller;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.repository.ProductRepository;
import com.quangduong.elasticsearch.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @GetMapping("test")
    public ResponseEntity<SearchResponse> searchProductTest(@RequestParam String name, @RequestParam String manufacturer) {
        return ResponseEntity.ok(productService.searchProduct(name, manufacturer));
    }

    @GetMapping
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String query) {
        return ResponseEntity.ok(productService.searchProduct(query));
    }

    @GetMapping("suggestions")
    public ResponseEntity<List<String>> getSuggestion(@RequestParam String query) {
        return ResponseEntity.ok(productService.fetchSuggestion(query));
    }
}
