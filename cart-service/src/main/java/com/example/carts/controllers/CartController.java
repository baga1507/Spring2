package com.example.carts.controllers;

import com.example.api.CartDto;
import com.example.carts.model.Cart;
import com.example.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
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

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
