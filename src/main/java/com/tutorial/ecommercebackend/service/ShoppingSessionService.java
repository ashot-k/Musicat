package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.shopping.SessionItem;

import java.util.List;

public interface ShoppingSessionService {
    List<SessionItem> findByShoppingSessionId(Long id);
    SessionItem addSessionItem(SessionItem sessionItem);
    void removeSessionItem(Long id);
}
