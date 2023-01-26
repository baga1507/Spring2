package com.example.Spring2.services;

import com.example.Spring2.dto.Cart;
import com.example.Spring2.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productService.getProduct(productId);
        tempCart.add(product);
    }

    public void decreaseItem(Long productId) {
        tempCart.decreaseItem(productId);
    }

    public void removeItem(Long productId) {
        tempCart.removeItem(productId);
    }

    public void clearCart() {
        tempCart.clear();
    }
}
