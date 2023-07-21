package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
