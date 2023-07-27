package com.tutorial.ecommercebackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/logged")
    String logged(HttpServletRequest req){
        if(req.isUserInRole("ADMIN"))
            return "redirect:/admin/admin-index";
        return "index";
    }
    @GetMapping("/login")
    String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    String login(){
        return "login";
    }

}
