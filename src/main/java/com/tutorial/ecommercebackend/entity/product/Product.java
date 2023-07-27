package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product",
        uniqueConstraints = {@UniqueConstraint(name = "unique_product_name", columnNames = "name")}
)
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message="Name cannot be empty")
    private String name;

    @Column(name = "artist", nullable = false)
    @NotBlank(message="Artist name cannot be empty")
    private String artist;


    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Insert valid price")
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inventory_id")

    private Inventory inventory;


    @Column(name = "year", nullable = false)
    @Min(value = 1900, message = "Enter valid year of release") @Max(value = 2030, message = "Enter valid year of release")
    @NotNull(message = "Enter valid year of release")
    private Integer year;

    @Column(name = "genre", nullable = false, length = 40)
    @NotEmpty(message="Genre cannot be empty")
    private String genre;

    public Product(String name, String artist, String description, Double price, Inventory inventory, Integer year, String genre) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.year = year;
        this.genre = genre;
    }

}