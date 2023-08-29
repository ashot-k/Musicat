package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Insert valid Quantity")
    private Integer quantity;

    public Inventory(Integer quantity) {
        this.quantity = quantity;
    }
    public Inventory(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}