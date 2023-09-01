package com.tutorial.ecommercebackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import java.io.IOException;

@Configuration
@EnableMethodSecurity
public class SecurityConfig implements AccessDeniedHandler {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    String[] staticResources = {
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/js/**",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/register").anonymous()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/item/**").permitAll()

        );
        http.formLogin(login -> {
            login
                    .loginPage("/login")
                    .defaultSuccessUrl("/after-login");
        });
        http.logout(logout -> {
            logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        });
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        );
        http.csrf().disable();

        return http.build();
    }
    @Bean
    public HttpSessionRequestCache httpSessionRequestCache() {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setCreateSessionAllowed(false);
        return requestCache;
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect("/");
    }
}
