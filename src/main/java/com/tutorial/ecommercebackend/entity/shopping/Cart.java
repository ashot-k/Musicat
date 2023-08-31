package com.tutorial.ecommercebackend.entity.shopping;

import com.tutorial.ecommercebackend.entity.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
    private List<Product> cartItems;

    public Cart(){
        this.cartItems = new ArrayList<>();
    }
    public Cart(List<Product> products){
        this.cartItems = products;
    }
    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
    }
}
