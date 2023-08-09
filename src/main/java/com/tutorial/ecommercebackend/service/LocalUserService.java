package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.user.LocalUser;

import java.util.List;

public interface LocalUserService {

   List<LocalUser> findAll();
   LocalUser saveUser(LocalUser user);

}
