package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Inventory;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.service.CartService;
import com.tutorial.ecommercebackend.service.LocalUserService;
import com.tutorial.ecommercebackend.service.ProductService;
import com.tutorial.ecommercebackend.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootTest
class UserControllerTest {


    @Autowired
    ProductRepository productRepository;
    @Test
    public void addTracks(){
        Product p1 = new Product("ok", "alright", "orksp", 15.0, new Inventory(5),  "ok");
        Track t = new Track("ok", p1);
        productRepository.saveAllAndFlush(List.of(p1));
        p1.getTracks().add(t);
        productRepository.save(p1);


        System.out.println("products: " + productRepository.findByKeyword("ok").size());
        System.out.println("tracks: " + productRepository.findByKeyword("ok").get(0).getTracks().size());
    }

}