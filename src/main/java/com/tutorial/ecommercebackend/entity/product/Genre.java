package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


/*

@Entity
@Getter
@Setter

@Table(name = "genre")
public class Genre {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Please select a genre")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
*/
