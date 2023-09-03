package com.tutorial.ecommercebackend.entity.shopping;

import com.tutorial.ecommercebackend.entity.product.Product;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.io.Serializable;

@Entity
@Table(name = "cart_item")
@Transactional
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long cartItemId;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    public CartItem() {

    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
