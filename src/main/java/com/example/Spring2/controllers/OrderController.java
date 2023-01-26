package com.example.Spring2.controllers;

import com.example.Spring2.dto.OrderDto;
import com.example.Spring2.entities.User;
import com.example.Spring2.services.OrderService;
import com.example.Spring2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        orderService.createOrder(user);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}
