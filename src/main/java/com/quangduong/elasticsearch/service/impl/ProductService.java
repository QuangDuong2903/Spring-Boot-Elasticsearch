package com.quangduong.elasticsearch.service.impl;

import com.quangduong.elasticsearch.dto.SearchResponse;
import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.repository.ProductRepository;
import com.quangduong.elasticsearch.service.IProductService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private static final String INDEX = "product";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public SearchResponse searchProduct(String name, String manufacturer) {
        SearchResponse response = new SearchResponse();
        response.setFindByName(productRepository.findByName(name));
        if (!name.contains(" "))
            response.setFindByNameContaining(productRepository.findByNameContaining(name));

        // NativeQuery
        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("name", name);

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Product> nativeQueryResults = elasticsearchOperations
                .search(query, Product.class, IndexCoordinates.of(INDEX));

        response.setSearchByNativeQuery(nativeQueryResults.getSearchHits().stream()
                .map(SearchHit::getContent).collect(Collectors.toList()));

        // String query
        Query stringQuery = new StringQuery(
                "{\"match\":{\"name\":{\"query\":\"" + name + "\"}}}\"");

        SearchHits<Product> stringQueryResults = elasticsearchOperations
                .search(stringQuery, Product.class, IndexCoordinates.of(INDEX));

        response.setSearchByStringQuery(stringQueryResults.getSearchHits().stream()
                .map(SearchHit::getContent).collect(Collectors.toList()));

        // Criteria query
        Criteria criteria = new Criteria("name").is(name).and("manufacturer").is(manufacturer);

        Query criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<Product> criteriaQueryResults = elasticsearchOperations
                .search(criteriaQuery, Product.class, IndexCoordinates.of(INDEX));

        response.setSearchByCriteriaQuery(criteriaQueryResults.getSearchHits().stream()
                .map(SearchHit::getContent).collect(Collectors.toList()));

        return response;
    }

    // Fuzzy search
    @Override
    public List<Product> searchProduct(String query) {

//        QueryBuilder queryBuilder = QueryBuilders
//                .fuzzyQuery("name", query)
//                .fuzziness(Fuzziness.ONE)
//                .prefixLength(3)
//                .transpositions(true);
//
//        Query searchQuery = new NativeSearchQueryBuilder()
//                .withFilter(queryBuilder)
//                .build();
//
//        SearchHits<Product> productSearchHits = elasticsearchOperations
//                .search(searchQuery, Product.class, IndexCoordinates.of(INDEX));


        QueryBuilder queryBuilder = QueryBuilders
                .multiMatchQuery(query, "name", "description")
                .fuzziness(Fuzziness.AUTO);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<Product> productSearchHits = elasticsearchOperations
                .search(searchQuery, Product.class, IndexCoordinates.of(INDEX));

        return productSearchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    // Wildcard search
    @Override
    public List<String> fetchSuggestion(String query) {

        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", query + "*");

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();

        SearchHits<Product> suggestion = elasticsearchOperations
                .search(searchQuery, Product.class, IndexCoordinates.of(INDEX));

        return suggestion.getSearchHits().stream().map(i -> i.getContent().getName()).collect(Collectors.toList());
    }


}
