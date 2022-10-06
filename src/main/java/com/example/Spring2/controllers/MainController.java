package com.example.Spring2.controllers;

import com.example.Spring2.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService service;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/products/{id}/info")
    public String info(Model model, @PathVariable Long id) {
        model.addAttribute("productFront", service.getProduct(id));
        return "info.html";
    }

    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("productList", service.getAllProducts());
        return "list.html";
    }
}
