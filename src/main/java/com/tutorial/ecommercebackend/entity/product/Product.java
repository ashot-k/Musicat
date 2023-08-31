package com.tutorial.ecommercebackend.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product implements Serializable{
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
    public Product(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false)
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


    @Column(name = "year_of_release", nullable = false)
    @NotNull(message = "Enter valid year of release")
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2030, message = "Year must be less than or equal to 2030")
    private Integer year;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Track> tracks;

    public List<String> tracksToStringList() {
        List<String> stringList = new ArrayList<>();
        for (Track t: tracks) {
            stringList.add(t.getName());
        }
        return stringList;
    }

    public String tracksToString() {
        StringBuilder allTracks = new StringBuilder();
        for (int i = 0; i < tracks.toArray().length; i++) {
            allTracks.append(tracks.get(i).getName()).append(", ");
        }
        allTracks.deleteCharAt(allTracks.length() - 2);
        allTracks.append("[" + tracks.size() + "]");
        return allTracks.toString();
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", year=" + year +
                ", tracks=" + tracks +
                '}';
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}