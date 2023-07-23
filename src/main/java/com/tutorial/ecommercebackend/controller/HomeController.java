package com.tutorial.ecommercebackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("theDate", new java.util.Date());
        return "index";
    }
}
