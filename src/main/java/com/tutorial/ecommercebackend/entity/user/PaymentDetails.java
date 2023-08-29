package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_payment")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_details_id", nullable = false)
    private Long id;

    @Column(name = "pay_method", nullable = false, length = 15)
    private String payMethod;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser localUser;

}