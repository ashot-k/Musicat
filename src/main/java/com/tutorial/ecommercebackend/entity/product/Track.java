package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "track")
public class Track implements Serializable {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
