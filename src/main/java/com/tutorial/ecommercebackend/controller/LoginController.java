package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.entity.user.Role;
import com.tutorial.ecommercebackend.security.CustomUserDetailsService;
import com.tutorial.ecommercebackend.security.SecurityUtils;
import com.tutorial.ecommercebackend.service.LocalUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.tutorial.ecommercebackend.security.SecurityUtils.checkDupleUsername;

@Controller
public class LoginController {
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
      /*  if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            return "index";
        }*/
        return "redirect:/";
    }

    @GetMapping("/register")
    String register(@ModelAttribute("user") LocalUser user) {
      /*  if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            return "index";
        }*/
        return "registration-form";
    }

    @PostMapping("/register")
    String registerUser(@Valid @ModelAttribute("user") LocalUser user,
                        BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            checkDupleUsername(userService, user.getUsername(), model);
            System.out.println("errors in user registration: " + result);
            return "registration-form";
        } else if (checkDupleUsername(userService, user.getUsername(), model)) {
            return "registration-form";
        }

        String purePassword = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        LocalUser savedUser = userService.saveUser(user);

        SecurityUtils.autoLogin(request, savedUser.getUsername(), purePassword);

        return "redirect:/";
    }

}
