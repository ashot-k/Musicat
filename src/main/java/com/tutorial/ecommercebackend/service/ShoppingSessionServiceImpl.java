package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.shopping.SessionItem;
import com.tutorial.ecommercebackend.repository.SessionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingSessionServiceImpl implements ShoppingSessionService{
    SessionItemRepository sessionItemRepository;

    @Autowired
    public ShoppingSessionServiceImpl(SessionItemRepository sessionItemRepository){
        this.sessionItemRepository = sessionItemRepository;
    }

    @Override
    public List<SessionItem> findByShoppingSessionId(Long id) {
        return sessionItemRepository.findByShoppingSessionId(id);
    }

    @Override
    public SessionItem addSessionItem(SessionItem sessionItem) {
       return sessionItemRepository.save(sessionItem);
    }

    @Override
    public void removeSessionItem(Long id){
        sessionItemRepository.deleteById(id);
    }

}
