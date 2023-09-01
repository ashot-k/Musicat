package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Product> {

}
