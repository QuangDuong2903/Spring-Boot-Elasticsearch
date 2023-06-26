package com.quangduong.elasticsearch.service.impl;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.repository.ProductRepository;
import com.quangduong.elasticsearch.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> createProduct(List<Product> products) {
        return (List<Product>) productRepository.saveAll(products);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public SearchResponse searchProduct(String name, Optional<String> category, Optional<String> manufacturer) {
        SearchResponse response = new SearchResponse();
        response.setFindByName(productRepository.findByName(name));
        response.setFindByNameContaining(productRepository.findByNameContaining(name));
        if (category.isPresent() && manufacturer.isPresent())
            response.setFindByManufacturerAndCategory(productRepository.findByManufacturerAndCategory(manufacturer.get(), category.get()));
        return response;
    }
}
