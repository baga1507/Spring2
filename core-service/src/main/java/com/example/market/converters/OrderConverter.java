package com.example.market.converters;

import com.example.api.OrderDto;
import com.example.market.entities.Order;
import com.example.market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;
    private final UserRepository userRepository;

    public OrderDto entityToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getUsername(),
                order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()),
                order.getTotalCost()
        );
    }

    public Order dtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUser(userRepository.findByUsername(orderDto.getUsername()).orElseThrow());
        order.setItems(orderDto.getItems().stream().map(item -> orderItemConverter.dtoToEntity(item, order)).collect(Collectors.toList()));
        order.setTotalCost(order.getTotalCost());
        return order;
    }
}
