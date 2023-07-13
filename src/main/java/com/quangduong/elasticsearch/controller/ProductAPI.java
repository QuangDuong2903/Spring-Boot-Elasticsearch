package com.quangduong.elasticsearch.controller;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin("http://localhost:3000")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @GetMapping("hello")
    public String hello() {
        return "Hello from server";
    }

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
        return ResponseEntity.ok(productService.fetchSuggestion(query.replace(' ', '_')));
    }
}
