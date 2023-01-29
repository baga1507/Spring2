package com.example.market.services;

import com.example.api.ProductDto;
import com.example.api.ResourceNotFoundException;
import com.example.market.converters.ProductConverter;
import com.example.market.entities.Product;
import com.example.market.repositories.ProductRepository;
import com.example.market.repositories.specifications.ProductSpecification;
import com.example.market.validators.ProductValidator;
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

        return productRepository.findAll(spec, PageRequest.of(p - 1, 10)).map(productConverter::entityToDto);
    }

    public ProductDto getProduct(Long id) {
        return productRepository.findById(id).map(productConverter::entityToDto).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " is not found"));
    }

    public ProductDto getProduct(String title) {
        return productRepository.findByTitle(title).map(productConverter::entityToDto).orElseThrow(() -> new ResourceNotFoundException("Product with title " + title + " is not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void changePrice(Long id, int delta) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " is not found"));
        product.setCost(product.getCost() + delta);
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
        product.setCost(productDto.getCost());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
