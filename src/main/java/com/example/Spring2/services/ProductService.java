package com.example.Spring2.services;

import com.example.Spring2.dto.Product;
import com.example.Spring2.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void changeCost(Long id, int delta) {
        Product product = getProduct(id);
        product.setCost(product.getCost() + delta);
    }

    public void addProduct(Long id, String title, Integer cost) {
        productRepository.save(new Product(id, title, cost));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
