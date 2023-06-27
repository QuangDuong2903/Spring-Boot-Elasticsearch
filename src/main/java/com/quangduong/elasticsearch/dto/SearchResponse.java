package com.quangduong.elasticsearch.dto;

import com.quangduong.elasticsearch.model.Product;

import java.util.List;

public class SearchResponse {

    private List<Product> findByName;

    private List<Product> findByNameContaining;

    private List<Product> findByManufacturerAndCategory;

    private List<Product> findByBrand;

    private List<Product> findByCategory;

    private List<Product> findByPrice;

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

    public List<Product> getFindByBrand() {
        return findByBrand;
    }

    public void setFindByBrand(List<Product> findByBrand) {
        this.findByBrand = findByBrand;
    }

    public List<Product> getFindByCategory() {
        return findByCategory;
    }

    public void setFindByCategory(List<Product> findByCategory) {
        this.findByCategory = findByCategory;
    }

    public List<Product> getFindByPrice() {
        return findByPrice;
    }

    public void setFindByPrice(List<Product> findByPrice) {
        this.findByPrice = findByPrice;
    }
}
