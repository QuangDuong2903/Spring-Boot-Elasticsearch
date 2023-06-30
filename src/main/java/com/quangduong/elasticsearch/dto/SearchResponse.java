package com.quangduong.elasticsearch.dto;

import com.quangduong.elasticsearch.model.Product;

import java.util.List;

public class SearchResponse {

    private List<Product> findByName;

    private List<Product> findByNameContaining;

    private List<Product> searchByNativeQuery;

    private List<Product> searchByStringQuery;

    private List<Product> searchByCriteriaQuery;

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

    public List<Product> getSearchByNativeQuery() {
        return searchByNativeQuery;
    }

    public void setSearchByNativeQuery(List<Product> searchByNativeQuery) {
        this.searchByNativeQuery = searchByNativeQuery;
    }

    public List<Product> getSearchByStringQuery() {
        return searchByStringQuery;
    }

    public void setSearchByStringQuery(List<Product> searchByStringQuery) {
        this.searchByStringQuery = searchByStringQuery;
    }

    public List<Product> getSearchByCriteriaQuery() {
        return searchByCriteriaQuery;
    }

    public void setSearchByCriteriaQuery(List<Product> searchByCriteriaQuery) {
        this.searchByCriteriaQuery = searchByCriteriaQuery;
    }
}
