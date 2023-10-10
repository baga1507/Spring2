package com.example.market.validators;

import com.example.api.ProductDto;
import com.example.api.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();

        if (productDto.getCost() < 1) {
            errors.add("The cost of a product cannot be less than 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("The title of a product cannot be blank");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
