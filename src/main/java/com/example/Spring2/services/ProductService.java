package com.example.Spring2.services;

import com.example.Spring2.converters.ProductConverter;
import com.example.Spring2.entities.Product;
import com.example.Spring2.dto.ProductDto;
import com.example.Spring2.exceptions.ResourceNotFoundException;
import com.example.Spring2.repositories.ProductRepository;
import com.example.Spring2.repositories.specifications.ProductSpecification;
import com.example.Spring2.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ProductConverter productConverter;

    public Page<ProductDto> find(Integer p, Integer minCost, Integer maxCost, String titlePart) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecification.costGreaterOrEqualTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecification.costLessOrEqualTo(maxCost));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }

        return productRepository.findAll(spec, PageRequest.of(p - 1, 5)).map(productConverter::entityToDto);
    }

    public ProductDto getProduct(Long id) {
        return productRepository.findById(id).map(productConverter::entityToDto).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " is not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void changePrice(Long id, int delta) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " is not found"));
        product.setPrice(product.getPrice() + delta);
    }

    public ProductDto addProduct(ProductDto productDto) {
        productValidator.validate(productDto);
        productRepository.save(productConverter.dtoToEntity(productDto));
        return productDto;
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with id " + productDto.getId() + " is not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
