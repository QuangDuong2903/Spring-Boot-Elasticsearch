package com.quangduong.elasticsearch.controller;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<List<Product>> createProduct(@RequestBody List<Product> products) {
        return new ResponseEntity<>(productService.createProduct(products), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SearchResponse> searchProduct(@RequestParam String name, @RequestParam Optional<String> category, @RequestParam Optional<String> manufacturer) {
        return ResponseEntity.ok(productService.searchProduct(name, category, manufacturer));
    }

}
