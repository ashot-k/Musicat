package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocalUserServiceImpl implements LocalUserService {
    LocalUserRepository userRep;

    @Autowired
    public LocalUserServiceImpl(LocalUserRepository userRep) {
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

    public LocalUser findbyUsername(String username) {
        Optional<LocalUser> user = userRep.findByUsername(username);
        if (user.isPresent())
            return user.get();
        else
            return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRep.existsByUsername(username);
    }


}
