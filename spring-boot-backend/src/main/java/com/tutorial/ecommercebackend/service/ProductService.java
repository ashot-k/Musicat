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
    List<Product> findAllProductsByArtist(String name);

    List<Product> findProductsByKeyword(String keyword);
    Page<Product> findProductsByKeywordPaged(String keyword, int pageNo, int pageSize);

    List<Product> findAllProducts();

    Page<Product> findAllProductsPaged(int pageNo, int pageSize);

    List<Product> findAllProductsByOrderByNameAsc();
    List<Product> findAllRelatedProducts(Long id);

    Product saveProduct(Product product);


    Optional<Product> findProductById(Long id);

    void deleteProductById(Long id);

    ///////////////////////////TRACKS///////////////////////////
    boolean saveTracks(Product p, List<String> trackNames);

    void deleteAllTracks(Product product);

    List<Track> saveAllTracks(List<Track> tracks);

    void removeTracksByIdIn(List<Long> ids);

    ///////////////////////////IMAGES///////////////////////////
    List<Images> findImagesByProduct(Product product);

    void deleteAllImages(Product product);

    void deleteImageById(Long id);

    void saveImage(Product product, MultipartFile file) throws IOException;

}
