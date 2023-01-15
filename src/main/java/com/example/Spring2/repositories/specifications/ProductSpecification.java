package com.example.Spring2.repositories.specifications;

import com.example.Spring2.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> costGreaterOrEqualTo(Integer price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> costLessOrEqualTo(Integer cost) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), cost));
    }

    public static Specification<Product> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }
}
