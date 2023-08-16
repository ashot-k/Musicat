package com.tutorial.ecommercebackend.security;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.repository.LocalUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private LocalUserRepository userRepository;
    public CustomUserDetailsService(LocalUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser user= userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
       return new CustomUserDetails(user);
    }
}
