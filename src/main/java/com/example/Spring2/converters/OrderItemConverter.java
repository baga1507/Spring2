package com.example.Spring2.converters;

import com.example.Spring2.dto.OrderItemDto;
import com.example.Spring2.entities.Order;
import com.example.Spring2.entities.OrderItem;
import com.example.Spring2.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {

    private final ProductService productService;
    private final ProductConverter productConverter;

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProduct().getTitle(),
                orderItem.getOrder().getId(),
                orderItem.getQuantity(),
                orderItem.getCostPerProduct(),
                orderItem.getCost()
        );
    }

    public OrderItem dtoToEntity(OrderItemDto orderItemDto, Order order) {
        return new OrderItem(
                orderItemDto.getId(),
                productConverter.dtoToEntity(productService.getProduct(orderItemDto.getProductTitle())),
                order,
                orderItemDto.getQuantity(),
                orderItemDto.getCostPerProduct(),
                orderItemDto.getCost()
        );
    }
}
