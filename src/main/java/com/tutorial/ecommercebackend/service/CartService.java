package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.user.LocalUser;

import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);
    Cart findByUsername(String username);

}
