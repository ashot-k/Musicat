package com.tutorial.ecommercebackend.entity.product;

import com.tutorial.ecommercebackend.repository.ImageRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(name = "artist", nullable = false)
    @NotBlank(message = "Artist name cannot be empty")
    private String artist;

    @Column(name = "genre", nullable = false)
    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Insert valid price")
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inventory_id")
    private Inventory inventory;

    @Column(name = "year", nullable = false)
    @Min(value = 1900, message = "Enter valid year of release")
    @Max(value = 2030, message = "Enter valid year of release")
    @NotNull(message = "Enter valid year of release")
    private Integer year;



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Track> tracks;

    public List<String> tracksToString() {
        List<String> stringList = new ArrayList<>();
        for (Track t: tracks) {
            stringList.add(t.getName());
        }
        return stringList;
    }

    public Product(String name, String artist, String description, Double price, Inventory inventory, Integer year, String genre, List<Track> tracks) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.year = year;
        this.genre = genre;
        this.tracks = tracks;
    }
}