package com.tutorial.ecommercebackend.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_payment")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, length = 15)
    private String type;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser localUser;

}