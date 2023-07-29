package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository products;

    @Autowired
    public ProductService(ProductRepository products) {
        this.products = products;
    }

    public List<Product> findAll(String keyword) {
        return (keyword != null) ? products.findByKeyword(keyword) : null;
    }

    public List<Product> findAll() {
        return products.findAll();
    }

    public Product save(Product product) {
        return products.save(product);
    }

    public List<Product> findAllByOrderByNameAsc() {
        return products.findAllByOrderByNameAsc();
    }

    public Optional<Product> findById(Long id) {
        return products.findById(id);
    }

    public void deleteById(Long id) {
        products.deleteById(id);
    }
}
