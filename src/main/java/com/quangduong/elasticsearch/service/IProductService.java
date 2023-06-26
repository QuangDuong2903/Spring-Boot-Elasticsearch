package com.quangduong.elasticsearch.service;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> createProduct(List<Product> products);
    Product createProduct(Product product);
    SearchResponse searchProduct(String name, Optional<String> category, Optional<String> manufacturer);

}
