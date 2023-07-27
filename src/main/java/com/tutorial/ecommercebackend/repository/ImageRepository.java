package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository  extends JpaRepository<Images, Long> {
    List<Images> findByProductId(Product product);
}
