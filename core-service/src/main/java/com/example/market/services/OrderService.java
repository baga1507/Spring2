package com.example.market.services;

import com.example.api.CartDto;
import com.example.api.OrderDto;
import com.example.api.ResourceNotFoundException;
import com.example.market.converters.OrderConverter;
import com.example.market.converters.ProductConverter;
import com.example.market.entities.Order;
import com.example.market.entities.OrderItem;
import com.example.market.entities.User;
import com.example.market.integrations.CartServiceIntegration;
import com.example.market.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartService;
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Transactional
    public void createOrder(User user) {
        CartDto cart = cartService.getCurrentCart();
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
