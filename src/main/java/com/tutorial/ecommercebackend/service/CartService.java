package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import com.tutorial.ecommercebackend.entity.user.LocalUser;

import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);
    Cart findByUsername(String username);
    CartItem addItem(Cart cart, CartItem cartItem);
    List<CartItem> addItems(Cart cart, List<CartItem> cartItems);
    Cart deleteById(long id);

}
