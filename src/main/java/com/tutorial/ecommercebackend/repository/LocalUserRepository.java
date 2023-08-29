package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
    Optional <LocalUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
