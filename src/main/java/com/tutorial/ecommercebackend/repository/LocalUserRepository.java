package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
    LocalUser findByUsername(String username);
}
