package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Insert valid Quantity")
    private Integer quantity;

    public Inventory(Integer quantity) {
        this.quantity = quantity;
    }

}