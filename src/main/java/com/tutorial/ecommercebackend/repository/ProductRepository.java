package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    // List<Product> findAll(String keyword);
    @Query(value = "SELECT * FROM product p WHERE p.product_name like %:keyword% or p.artist like %:keyword% or p.genre like %:keyword%"
            , nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);
    @Query(value = "SELECT * FROM product p WHERE p.product_name like %:keyword% or p.artist like %:keyword% or p.genre like %:keyword%"
            , nativeQuery = true)
    Page<Product> findByKeywordPageable(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE p.product_name like %:keyword%"
            , nativeQuery = true)
    Page<Product> findByName(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE p.genre like %:keyword%"
            , nativeQuery = true)
    Page<Product> findByGenre(@Param("keyword") String keyword, Pageable pageable);


    List<Product> findAllByArtist(String name);

    List<Product> findAllByOrderByNameAsc();
}
