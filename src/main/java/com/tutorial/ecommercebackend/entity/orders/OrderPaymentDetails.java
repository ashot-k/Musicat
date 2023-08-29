package com.tutorial.ecommercebackend.entity.orders;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_details")
public class OrderPaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_pay_details_id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private OrderDetails orderId;

    @Column(name = "amount", nullable = false)
    private Double amount;

}
