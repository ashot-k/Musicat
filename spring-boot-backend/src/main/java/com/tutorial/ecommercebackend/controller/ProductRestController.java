package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import com.tutorial.ecommercebackend.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/products")
@RestController
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:8080"})
public class ProductRestController {
    ProductService productService;
    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> p = productService.findAllProducts();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping("/related-products/{productId}")
    public ResponseEntity<List<Product>> getRelatedProducts(@PathVariable String productId){

        return null;
    }
    @GetMapping("/search-product/{keyword}")
    public ResponseEntity<List<Product>> search(@PathVariable String keyword){
        return new ResponseEntity<>(productService.findProductsByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public  ResponseEntity<Product> getProduct(@PathVariable String productId){
        Optional<Product> p = productService.findProductById(Long.valueOf(productId));
        if(p.isPresent())
            return new ResponseEntity<>(p.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

