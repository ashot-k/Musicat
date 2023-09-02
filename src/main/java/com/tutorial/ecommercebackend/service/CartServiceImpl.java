package com.tutorial.ecommercebackend.service;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import com.tutorial.ecommercebackend.repository.CartItemRepository;
import com.tutorial.ecommercebackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    CartRepository cartRep;
    CartItemRepository cartItemRep;

    @Autowired
    public CartServiceImpl(CartRepository cartRep, CartItemRepository cartItemRep) {
        this.cartRep = cartRep;
        this.cartItemRep = cartItemRep;
    }

    @Override
    public CartItem addItem(Cart cart, CartItem cartItem) {
        CartItem savedCartItem = null;
        if (!cart.getCartItems().isEmpty()) {
            for (CartItem preCartItem : cart.getCartItems()) {
                if (cartItem.getProduct().getId().longValue() == preCartItem.getProduct().getId().longValue()) {
                    preCartItem.setQuantity(preCartItem.getQuantity() + 1);
                    savedCartItem = cartItemRep.save(preCartItem);
                }
            }
        }
        else{
            savedCartItem = cartItemRep.save(cartItem);
            cart.getCartItems().add(savedCartItem);
            cartRep.save(cart);
        }
        return savedCartItem;
    }

    @Override
    public List<CartItem> addItems(Cart cart, List<CartItem> cartItems) {
        List<CartItem> savedItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            savedItems.add(addItem(cart, cartItem));
        }
        return savedItems;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRep.save(cart);
    }

    @Override
    public Cart findByUsername(String username) {
        Optional<Cart> cart = cartRep.findByLocalUser_Username(username);
        if (cart.isPresent())
            return cart.get();
        return null;
    }

}
