package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class LocalUserServiceImpl implements LocalUserService{
    LocalUserRepository userRep;
    @Autowired
    public LocalUserServiceImpl(LocalUserRepository userRep){
        this.userRep = userRep;
    }
    @Override
    public List<LocalUser> findAll() {
        return userRep.findAll();
    }

    @Override
    public LocalUser saveUser(LocalUser user) {
       return userRep.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRep.existsByUsername(username);
    }




}
