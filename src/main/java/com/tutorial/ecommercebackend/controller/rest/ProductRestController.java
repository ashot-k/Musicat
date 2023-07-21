package com.tutorial.ecommercebackend.controller.rest;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
/*
    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    List<Product> findAll(Model model) {
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    Product findProduct(Model model, @PathVariable Long productId) {
        return productService.findById(productId);
    }

    @PostMapping("/products")
    Product addProduct(@RequestBody Product product) {
        product.setId(0L);
        Product savedProduct = productService.save(product);
        return savedProduct;
    }

 */
}
