package com.tutorial.ecommercebackend.entity.shopping;


import com.tutorial.ecommercebackend.entity.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "session_item")
public class SessionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ShoppingSession shoppingSession;
    @Column(name = "quantity")
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
