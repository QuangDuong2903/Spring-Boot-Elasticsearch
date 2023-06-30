package com.quangduong.elasticsearch.service;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    SearchResponse searchProduct(String name, String manufacturer);
    List<Product> searchProduct(String query);
    List<String> fetchSuggestion(String query);
}
