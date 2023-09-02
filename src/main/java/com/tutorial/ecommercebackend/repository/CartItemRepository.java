package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
