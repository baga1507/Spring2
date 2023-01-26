package com.example.Spring2.controllers;

import com.example.Spring2.dto.ProductDto;
import com.example.Spring2.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/{id}/info")
    @ResponseBody
    public String info(Model model, @PathVariable Long id) {
        model.addAttribute("productFront", service.getProduct(id));
        return "info.html";
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_price", required = false) Integer minPrice,
                                        @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam(name = "title_part", required = false) String titlePart) {
        if (page < 1) {
            page = 1;
        }
        return service.find(page, minPrice, maxPrice, titlePart);
    }

    @GetMapping("/change_cost")
    public void changePrice(@RequestParam Long productId, @RequestParam int delta) {
        service.changePrice(productId, delta);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return service.addProduct(productDto);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        service.updateProduct(productDto);
    }

    @DeleteMapping("/{productId}/delete")
    @ResponseBody
    public void deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
    }
}
