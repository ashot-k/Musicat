package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "track")
public class Track {


    public Track(String name, Product product) {
        this.name = name;
        this.product = product;
    }
    public Track(String name) {
        this.name = name;
    }
    public Track() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", nullable = false)
    private Long id;


    @Column(name = "track_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
