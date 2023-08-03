package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "imagedata", columnDefinition="VARCHAR(255)")
    private String image;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
