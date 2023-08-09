package com.tutorial.ecommercebackend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
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
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/item/**").permitAll()
        );
        http.formLogin(login -> {
            login
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll();
        });
       http.logout(logout -> {
            logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        });
        http.csrf().disable();
        return http.build();
    }


   /* @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/
}
