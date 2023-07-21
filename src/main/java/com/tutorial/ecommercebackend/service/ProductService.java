package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.product.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    void deleteById(Long id);
    List<Product> findAll();
}
