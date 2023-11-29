package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.shopping.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart deleteById(long id);
    Optional <Cart> findByLocalUser_Username(String username);
}
