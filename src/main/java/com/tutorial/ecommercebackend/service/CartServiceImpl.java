package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.repository.CartRepository;
import com.tutorial.ecommercebackend.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRep;

    @Autowired
    public CartServiceImpl(CartRepository cartRep) {
        this.cartRep = cartRep;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRep.save(cart);
    }

    @Override
    public Cart findByUsername(String username) {
        Optional<Cart> c = cartRep.findByLocalUser_Username(username);
        if(c.isPresent())
            return c.get();
        return null;
    }

}
