package com.example.Spring2.repositories;

import com.example.Spring2.dto.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk", 75),
                new Product(2L, "Salt", 10000),
                new Product(3L, "Butter", 130),
                new Product(4L, "Bread", 25),
                new Product(5L, "Alenka", 80)
        ));
    }

    public Product findById(Long id) {
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Product> getAll() {
        return productList;
    }

    public void add(Long id, String title, int cost) {
        productList.add(new Product(id, title, cost));
    }

    public void delete(Long id) {
        productList.removeIf(p -> p.getId().equals(id));
    }
}
