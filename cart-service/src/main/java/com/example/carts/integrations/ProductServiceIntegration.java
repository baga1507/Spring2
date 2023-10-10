package com.example.carts.integrations;

import com.example.api.ProductDto;
import com.example.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    public ProductDto getProduct(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus == HttpStatus.NOT_FOUND,
                        clientResponse -> Mono.error(new ResourceNotFoundException("The product is not found in MS"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
