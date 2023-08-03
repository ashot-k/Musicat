package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    // List<Product> findAll(String keyword);
    @Query(value = "SELECT * FROM product p WHERE p.name like %:keyword% or p.artist like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);


    List<Product> findAllByArtist(String name);

    List<Product> findAllByOrderByNameAsc();
}
