package com.quangduong.elasticsearch.dto;

import com.quangduong.elasticsearch.model.Product;

import java.util.List;

public class SearchResponse {

    private List<Product> findByName;

    private List<Product> findByNameContaining;

    private List<Product> findByManufacturerAndCategory;

    public List<Product> getFindByName() {
        return findByName;
    }

    public void setFindByName(List<Product> findByName) {
        this.findByName = findByName;
    }

    public List<Product> getFindByNameContaining() {
        return findByNameContaining;
    }

    public void setFindByNameContaining(List<Product> findByNameContaining) {
        this.findByNameContaining = findByNameContaining;
    }

    public List<Product> getFindByManufacturerAndCategory() {
        return findByManufacturerAndCategory;
    }

    public void setFindByManufacturerAndCategory(List<Product> findByManufacturerAndCategory) {
        this.findByManufacturerAndCategory = findByManufacturerAndCategory;
    }
}
