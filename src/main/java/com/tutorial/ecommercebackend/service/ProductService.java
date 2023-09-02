package com.tutorial.ecommercebackend.service;


import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    ///////////////////////////PRODUCTS///////////////////////////
    public List<Product> findAllProductsByArtist(String name);

    public List<Product> findProductsByKeyword(String keyword);
    public Page<Product> findProductsByKeywordPaged(String keyword, int pageNo, int pageSize);

    public List<Product> findAllProducts();

    public Page<Product> findAllProductsPaged(int pageNo, int pageSize);

    public List<Product> findAllProductsByOrderByNameAsc();

    public Product saveProduct(Product product);


    public Optional<Product> findProductById(Long id);

    public void deleteProductById(Long id);

    ///////////////////////////TRACKS///////////////////////////
    public void saveTracks(Product p, List<String> trackNames);

    public void deleteAllTracks(Product product);

    public List<Track> saveAllTracks(List<Track> tracks);

    public void removeTracksByIdIn(List<Long> ids);

    ///////////////////////////IMAGES///////////////////////////
    public List<Images> findImagesByProduct(Product product);

    public void deleteAllImages(Product product);

    public void deleteImageById(Long id);

    public void saveImage(Product product, MultipartFile file) throws IOException;

}
