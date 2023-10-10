package com.example.carts.converters;

import com.example.api.CartItemDto;
import com.example.carts.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto modelToDto(CartItem cartItemDto) {
        return new CartItemDto(
                cartItemDto.getProductId(),
                cartItemDto.getProductTitle(),
                cartItemDto.getQuantity(),
                cartItemDto.getCostPerProduct(),
                cartItemDto.getCost()
        );
    }
}
