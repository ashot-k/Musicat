package com.tutorial.ecommercebackend.controller;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class HomeController {



    @GetMapping("/")
    String home() {
        return "index";
    }




}
