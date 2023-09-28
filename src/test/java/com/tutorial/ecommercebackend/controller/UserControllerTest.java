package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.service.CartService;
import com.tutorial.ecommercebackend.service.LocalUserService;
import com.tutorial.ecommercebackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserControllerTest {

    ProductService productService;
    CartService cartService;
    LocalUserService userService;
    @Autowired
    public UserControllerTest(ProductService productService, CartService cartService, LocalUserService userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
      //  this.currentPageProducts = new ArrayList<>();
    }
    @Test
    void addToCart() {
       UserController controller = new UserController(productService,cartService,userService);
        System.out.println();
    }
}