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
        for (CartItem preCartItems : cart.getCartItems()) {
            if (cartItem.getProduct().getId().longValue() == preCartItems.getProduct().getId().longValue()) {
                preCartItems.setQuantity(preCartItems.getQuantity() + 1);
            } else {
                CartItem savedCartItem = cartItemRep.save(cartItem);
                cart.getCartItems().add(savedCartItem);
            }
        }
        cartRep.save(cart);
        return cartItem;
    }

    @Override
    public List<CartItem> addItems(Cart cart, List<CartItem> cartItems) {
        List<CartItem> savedItems = new ArrayList<>();
        for (CartItem c : cartItems) {
            savedItems.add(addItem(cart, c));
        }
       /* List<CartItem> savedItems = cartItemRep.saveAll(cartItems);
        cart.getCartItems().addAll(savedItems);
        cartRep.save(cart);*/
        return savedItems;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRep.save(cart);
    }

    @Override
    public Cart findByUsername(String username) {
        Optional<Cart> c = cartRep.findByLocalUser_Username(username);
        if (c.isPresent())
            return c.get();
        return null;
    }

}
