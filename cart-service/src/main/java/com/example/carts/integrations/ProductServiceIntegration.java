package com.example.carts.integrations;

import com.example.api.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceIntegration {

    public ProductDto getProduct(Long id) {
        return new RestTemplate().getForObject("http://localhost:8189/app/api/v1/products/" + id, ProductDto.class);
    }
}
