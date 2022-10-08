package com.example.Spring2.services;

import com.example.Spring2.dto.Product;
import com.example.Spring2.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public void changeCost(Long id, int delta) {
        Product product = getProduct(id);
        product.setCost(product.getCost() + delta);
    }
}
