package com.tutorial.ecommercebackend.entity.shopping;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    public static final int TIMEOUT_IN_SECONDS = 30   * 1000;
    public Cart() {
        this.cartItems = new ArrayList<>();
        setupTimers();
    }
    public Cart(LocalUser user) {
        this.cartItems = new ArrayList<>();
        this.localUser = user;
        setupTimers();
    }

    public void setupTimers(){
        this.timeCreated = new Timestamp(System.currentTimeMillis());
        this.lastAccessed = new Timestamp(System.currentTimeMillis());
        this.remaining = new Timestamp(TIMEOUT_IN_SECONDS);
    }
    public void acccessed(){
        this.lastAccessed = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "cart_user")
    private LocalUser localUser;
    @Column(name = "time_created")
    private Timestamp timeCreated;
    @Column(name = "last_accessed")
    private Timestamp lastAccessed;
    @Column(name = "remaining")
    private Timestamp remaining;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_item_mapping",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id")
    )
    private List<CartItem> cartItems = new ArrayList<>();


    public String getTotalItems() {
        int total = 0;
        for (CartItem cartItem : cartItems) {
           total = cartItem.getQuantity();
        }
        return String.valueOf(total);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Timestamp getRemaining() {
        return remaining;
    }

    public void setRemaining(Timestamp remaining) {
        this.remaining = remaining;
    }

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
