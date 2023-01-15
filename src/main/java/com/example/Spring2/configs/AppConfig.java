package com.example.Spring2.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secret.properties")
public class AppConfig {

}
