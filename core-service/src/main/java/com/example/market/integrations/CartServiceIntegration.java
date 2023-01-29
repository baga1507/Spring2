package com.example.market.integrations;

import com.example.api.CartDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartServiceIntegration {

    public CartDto getCurrentCart() {
        return new RestTemplate().getForObject("http://localhost:8190/app/api/v1/cart", CartDto.class);
    }

    public void clearCart() {
        new RestTemplate().delete("http://localhost:8190/app/api/v1/cart/clear");
    }
}
