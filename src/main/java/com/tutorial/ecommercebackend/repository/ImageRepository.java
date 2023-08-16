package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository  extends JpaRepository<Images, Long> {
    List<Images> findByProduct(Product product);
}
