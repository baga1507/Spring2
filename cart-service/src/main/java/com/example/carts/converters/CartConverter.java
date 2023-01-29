package com.example.carts.converters;

import com.example.api.CartDto;
import com.example.carts.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto modelToEntity(Cart cart) {
        return new CartDto(
                cart.getItems().stream().map(cartItemConverter::modelToDto).collect(Collectors.toList()),
                cart.getTotalCost()
        );
    }
}
