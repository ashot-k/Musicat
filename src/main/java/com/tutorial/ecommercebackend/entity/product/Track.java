package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Track(String name) {
        this.name = name;
    }

    public Track(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    public Track() {
    }
}
