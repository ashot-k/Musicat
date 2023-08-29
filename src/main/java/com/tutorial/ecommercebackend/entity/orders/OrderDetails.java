package com.tutorial.ecommercebackend.entity.orders;

import com.tutorial.ecommercebackend.entity.user.LocalUser;
import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "fk_user_id",
            nullable = false
    )
    private LocalUser localUser;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private OrderPaymentDetails orderPaymentDetails;

}
