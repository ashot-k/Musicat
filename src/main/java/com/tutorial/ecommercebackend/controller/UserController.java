package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    ProductRepository products;

    @Autowired
    public UserController(ProductRepository products) {
        this.products = products;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().toString().contains("ADMIN"))
            model.addAttribute("ADMIN", 1);
            */

        model.addAttribute("products", products.findAllByOrderByNameAsc());
        return "index";
    }


}
