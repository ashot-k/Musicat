package com.tutorial.ecommercebackend.repository;


import com.tutorial.ecommercebackend.entity.shopping.SessionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionItemRepository extends JpaRepository<SessionItem, Long> {

    List<SessionItem> findByShoppingSessionId(Long id);

}
