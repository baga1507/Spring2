package com.example.carts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductServiceIntegrationProperties {

    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
