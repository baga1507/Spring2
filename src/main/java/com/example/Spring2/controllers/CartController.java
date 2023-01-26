package com.example.Spring2.controllers;

import com.example.Spring2.dto.Cart;
import com.example.Spring2.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/decrease/{id}")
    public void decreaseItem(@PathVariable Long id) {
        cartService.decreaseItem(id);
    }

    @GetMapping("/remove/{id}")
    public void removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
