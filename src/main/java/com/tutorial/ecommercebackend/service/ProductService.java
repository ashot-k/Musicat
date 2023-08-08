package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ImageRepository;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    ///////////////////////////TRACKS///////////////////////////

    public void handleTracks(Product p, List<String> trackNames);
    public void deleteAllTracks(Product product);
    public List<Track> saveAllTracks(List<Track> tracks);
    public List<Track> findTracksByProductId(Product product);

    public void removeTracksByIdIn(List<Long> ids);
    ///////////////////////////IMAGES///////////////////////////
    public List<Images> findImagesByProduct(Product product);
    public void deleteAllImages(Product product);

    public void deleteImageById(Long id);
    public void saveImage(Product product, MultipartFile file) throws IOException;
    ///////////////////////////PRODUCTS///////////////////////////

    public List<Product> findAllProductsByArtist(String name);
    public List<Product> findAllProducts(String keyword);
    public List<Product> findAllProducts(int pageNo, int pageSize);
    public Page<Product> findAllProductsPages(int pageNo, int pageSize);
    public Product saveProduct(Product product);

    public List<Product> findAllProductsByOrderByNameAsc();

    public Optional<Product> findProductById(Long id);

    public void deleteProductById(Long id);
}
