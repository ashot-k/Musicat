package com.tutorial.ecommercebackend.security;

import com.tutorial.ecommercebackend.controller.UserController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class LoginRedirectsHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Collection<? extends GrantedAuthority> role = authentication.getAuthorities();

        for (GrantedAuthority a : role) {
            if (a.getAuthority().equals("ROLE_ADMIN"))
                response.sendRedirect(UserController.indexUrl + "/admin");
            else
                response.sendRedirect(UserController.indexUrl);
        }
    }
}
