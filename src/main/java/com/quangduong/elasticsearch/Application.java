package com.quangduong.elasticsearch;

import com.quangduong.elasticsearch.model.Product;
import com.quangduong.elasticsearch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {

    @Autowired
    private ProductRepository productRepository;

    @PreDestroy
    public void deleteProducts() {
        productRepository.deleteAll();
    }

    @PostConstruct
    public void prepareProducts() throws IOException {
        Resource resource = new ClassPathResource("products.csv");
        List<Product> products = new ArrayList<>();

        Scanner scanner = new Scanner(resource.getInputStream());
        int i = 1;
        while (scanner.hasNextLine()) {
            Scanner row = new Scanner(scanner.nextLine());
            row.useDelimiter(",");
            while (i != 1 && row.hasNext())
                try {
                    String name = row.next();
                    String desc = row.next();
                    String manufacturer = row.next();
                    products.add(new Product(name, desc, manufacturer));
                } catch (Exception e) {

                }
            i++;
        }
		productRepository.saveAll(products);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
