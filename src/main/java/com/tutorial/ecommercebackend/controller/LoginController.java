package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.entity.user.Role;
import com.tutorial.ecommercebackend.service.LocalUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController  {
    LocalUserService userService;

    @Autowired
    public LoginController(LocalUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    String login() {
        System.out.println("loginpost");
        return "login";
    }

    @GetMapping("/register")
    String register(@ModelAttribute("user") LocalUser user) {
        return "registration-form";
    }

    @PostMapping("/register")
    String registerUser(@Valid @ModelAttribute("user") LocalUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            checkDupleUsername(user.getUsername(), model);
            System.out.println("errors in user registration: " + result);
            return "registration-form";
        } else if (checkDupleUsername(user.getUsername(), model)) {
            return "registration-form";
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        userService.saveUser(user);

        return "index";
    }

    public boolean checkDupleUsername(String username, Model model) {
        if (userService.existsByUsername(username)) {
            model.addAttribute("dupleUsername"
                    , "Username " + username + " has already been taken");
            System.out.println("duple username " + username);
            return true;
        }
        return false;
    }

}
