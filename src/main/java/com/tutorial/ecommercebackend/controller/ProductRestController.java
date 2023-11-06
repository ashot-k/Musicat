package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import com.tutorial.ecommercebackend.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/products")
@RestController
public class ProductRestController {
    ProductService productService;
    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public  ResponseEntity<Product> getProduct(@PathVariable String productId){
        Optional<Product> p = productService.findProductById(Long.valueOf(productId));
        if(p.isPresent())
            return new ResponseEntity<>(p.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
