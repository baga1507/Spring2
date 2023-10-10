package com.example.carts.services;

import com.example.api.CartDto;
import com.example.api.ProductDto;
import com.example.carts.converters.CartConverter;
import com.example.carts.integrations.ProductServiceIntegration;
import com.example.carts.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartConverter cartConverter;
    private final ProductServiceIntegration productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public CartDto getCurrentCart() {
        return cartConverter.modelToEntity(tempCart);
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
