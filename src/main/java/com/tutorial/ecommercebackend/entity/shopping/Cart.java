package com.tutorial.ecommercebackend.entity.shopping;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @OneToMany
    private List<CartItem> cartItems;
    @OneToOne
    @JoinColumn(name = "cart_user")
    private LocalUser localUser;


    public Cart() {
        this.cartItems = new ArrayList<>();
        System.out.println("called cart constructor");
    }
    public Cart(LocalUser user){
        this.cartItems = new ArrayList<>();
        this.localUser = user;
        System.out.println("called cart constructor");
    }









    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }



    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getTotalItems() {
        return String.valueOf(cartItems.size());
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
