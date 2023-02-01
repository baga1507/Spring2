package com.example.market.integrations;

import com.example.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Calendar;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceClient;

    public CartDto getCurrentCart() {
        return cartServiceClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearCart() {
        cartServiceClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
