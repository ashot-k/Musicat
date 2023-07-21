package com.tutorial.ecommercebackend.entity.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment_details")
public class OrderPaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private OrderDetails orderId;

    @Column(name = "amount", nullable = false)
    private Double amount;

}
