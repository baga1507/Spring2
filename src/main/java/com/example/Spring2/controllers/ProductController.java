package com.example.Spring2.controllers;

import com.example.Spring2.dto.Product;
import com.example.Spring2.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/products/{id}/info")
    public String info(Model model, @PathVariable Long id) {
        model.addAttribute("productFront", service.getProduct(id));
        return "info.html";
    }

    @GetMapping("/products/all")
    @ResponseBody
    public List<Product> list() {
        return service.getAllProducts();
    }

    @GetMapping("/products/change_cost")
    @ResponseBody
    public void changeCost(@RequestParam Long productId, @RequestParam int delta) {
        service.changeCost(productId, delta);
    }

    @GetMapping("/products/add")
    public void addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam Integer cost) {
        service.addProduct(id, title, cost);
    }

    @GetMapping("/products/{productId}/delete")
    @ResponseBody
    public void deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
    }
}
