package com.example.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productTitle;
    private Long orderId;
    private int quantity;
    private int costPerProduct;
    private int cost;
}
