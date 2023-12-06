package com.tutorial.ecommercebackend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    public static final String IMAGE_URL_PREFIX =  "/images/album-images/";
    public static final String IMAGE_URL_SUFFIX = "/album_image.png";

    public Product(String name, String artist, String description, Double price, Inventory inventory,String genre, List<Track> tracks) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.genre = genre;
        this.tracks = tracks;
    }
    public Product(String name, String artist, String description, Double price, Inventory inventory, String genre) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.genre = genre;
        this.tracks = new ArrayList<>();
    }
    public Product(String name, String artist, Double price, Inventory inventory, String genre){
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.inventory = inventory;
        this.genre = genre;
        this.tracks = new ArrayList<>();
    }
    public Product(String name, String artist, Double price, Inventory inventory, String genre,List<Track> tracks){
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.inventory = inventory;
        this.genre = genre;
        this.tracks = tracks;
    }
    public Product(){
        this.tracks = new ArrayList<>();
    }

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

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Enter valid price")
    @Digits(message = "Enter valid price", integer = 6, fraction = 0)
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inventory_id")
    private Inventory inventory;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Track> tracks;

    private String imageURL;

    public List<String> tracksToStringList() {
        List<String> stringList = new ArrayList<>();
        for (Track t: tracks) {
            stringList.add(t.getName());
        }
        return stringList;
    }

    public String tracksToString() {
        if(tracks.isEmpty()){
            return "!EMPTY!";
        }
        StringBuilder allTracks = new StringBuilder();
        for (int i = 0; i < tracks.toArray().length; i++) {
            allTracks.append(tracks.get(i).getName()).append(", ");
        }
        allTracks.deleteCharAt(allTracks.length() - 2);
        allTracks.append("[").append(tracks.size()).append("]");
        return allTracks.toString();
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL(){
        return this.imageURL;
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


    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}