package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ImageRepository;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository products;
    ImageRepository imageRep;
    TrackRepository trackRep;

    @Autowired
    public ProductService(ProductRepository products, ImageRepository images, TrackRepository trackRep) {
        this.products = products;
        this.imageRep = images;
        this.trackRep = trackRep;
    }
    ///////////////////////////TRACKS///////////////////////////
    public void deleteAllTracks(Product product){
        trackRep.deleteAll(trackRep.findByProductId(product.getId()));
    }
    public List<Track> saveAllTracks(List<Track> tracks){
        return trackRep.saveAll(tracks);
    }
    public List<Track> findTracksByProductId(Product product){
        return trackRep.findByProductId(product.getId());
    }

    public void removeTracksByIdIn(List<Long> ids){
        trackRep.removeByIdIn(ids);
    }
    ///////////////////////////IMAGES///////////////////////////
    public List<Images> findImagesByProduct(Product product){
       return imageRep.findByProduct(product);
    }
    public void deleteAllImages(Product product){
        imageRep.deleteAll(imageRep.findByProduct(product));
    }

    public void deleteImageById(Long id){
        imageRep.deleteById(id);
    }
    public void saveImage(Product product, MultipartFile file) throws IOException {
        String uploadDir = "./src/main/resources/static/images/album-images/" + product.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        Path filePath = null;
        try (InputStream inputStream = file.getInputStream()) {
            filePath = uploadPath.resolve("album_image.png");
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath);
        } catch (IOException e) {
            System.out.println("failed saving file: " + e);
        }

        Images image = new Images();
        image.setProduct(product);
        String imageURIrel = String.valueOf(filePath).substring(String.valueOf(filePath).indexOf("static") + 6);
        image.setImage(imageURIrel);
        deleteAllImages(product);
        imageRep.save(image);
    }
    ///////////////////////////PRODUCTS///////////////////////////

    public List<Product> findAllProductsByArtist(String name){
        return products.findAllByArtist(name);
    }
    public List<Product> findAllProducts(String keyword) {
        return (keyword != null) ? products.findByKeyword(keyword) : null;
    }
    public List<Product> findAllProducts() {
        return products.findAll();
    }

    public Product saveProduct(Product product) {
        return products.save(product);
    }

    public List<Product> findAllProductsByOrderByNameAsc() {
        return products.findAllByOrderByNameAsc();
    }

    public Optional<Product> findProductById(Long id) {
        return products.findById(id);
    }

    public void deleteProductById(Long id) {
        products.deleteById(id);
    }
}
