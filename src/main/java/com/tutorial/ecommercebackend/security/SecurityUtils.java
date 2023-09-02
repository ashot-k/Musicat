package com.tutorial.ecommercebackend.security;

import com.tutorial.ecommercebackend.controller.UserController;
import com.tutorial.ecommercebackend.service.LocalUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public class SecurityUtils {

    //e.g After registration
    public static void autoLogin(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while login " + e);
        }
    }
    public static boolean checkDupleUsername(LocalUserService userService, String username, Model model) {
        if (userService.existsByUsername(username)) {
            model.addAttribute("dupleUsername"
                    , "Username " + username + " has already been taken");
            System.out.println("duple username " + username);
            return true;
        }
        return false;
    }
}
