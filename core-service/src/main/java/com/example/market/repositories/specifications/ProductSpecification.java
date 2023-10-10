package com.example.market.repositories.specifications;

import com.example.market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> costGreaterOrEqualTo(Integer cost) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> costLessOrEqualTo(Integer cost) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }
}
