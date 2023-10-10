package com.example.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private int costPerProduct;
    private int cost;
}
