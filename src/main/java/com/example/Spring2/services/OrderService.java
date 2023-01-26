package com.example.Spring2.services;

import com.example.Spring2.converters.OrderConverter;
import com.example.Spring2.converters.ProductConverter;
import com.example.Spring2.dto.Cart;
import com.example.Spring2.dto.OrderDto;
import com.example.Spring2.entities.Order;
import com.example.Spring2.entities.OrderItem;
import com.example.Spring2.entities.User;
import com.example.Spring2.exceptions.ResourceNotFoundException;
import com.example.Spring2.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Transactional
    public void createOrder(User user) {
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalCost(cart.getTotalCost());
        order.setItems(cart.getItems().stream().map(
                cartItem -> new OrderItem(
                        productConverter.dtoToEntity(productService.getProduct(cartItem.getProductId())),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getCostPerProduct(),
                        cartItem.getCost()
                        )
                ).collect(Collectors.toList())
        );
        orderRepository.save(order);
        cartService.clearCart();
    }

    public OrderDto getOrder(Long id) {
        return orderRepository.findById(id).map(orderConverter::entityToDto).orElseThrow(() -> new ResourceNotFoundException("Заказ с id " + id + " не найден"));
    }
}
