package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import com.tutorial.ecommercebackend.repository.CartItemRepository;
import com.tutorial.ecommercebackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        if (!cart.getCartItems().isEmpty()) {
            for (CartItem preCartItem : cart.getCartItems()) {
                if (cartItem.getProduct().getId().equals(preCartItem.getProduct().getId())) {
                    preCartItem.setQuantity(preCartItem.getQuantity() + cartItem.getQuantity());
                    return cartItemRep.save(preCartItem);
                }
            }
        }
        CartItem savedItem = cartItemRep.save(cartItem);
        List<CartItem> newCartItems = new ArrayList<>();
        newCartItems.addAll(cart.getCartItems());
        newCartItems.add(savedItem);
        cart.setCartItems(newCartItems);
        saveCart(cart);
        return savedItem;
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
