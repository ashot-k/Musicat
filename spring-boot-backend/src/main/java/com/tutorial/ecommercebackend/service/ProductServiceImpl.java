package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ImageRepository;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    ProductRepository productRep;
    ImageRepository imageRep;
    TrackRepository trackRep;

    String imageStorageUrl = "./src/main/resources/static/images/album-images/";
    public static int totalPages;

    @Autowired
    public ProductServiceImpl(ProductRepository products, ImageRepository images, TrackRepository trackRep) {
        this.productRep = products;
        this.imageRep = images;
        this.trackRep = trackRep;
    }

    ///////////////////////////PRODUCTS///////////////////////////
    public List<Product> findAllProductsByArtist(String name) {
        return productRep.findAllByArtist(name);
    }

    public List<Product> findProductsByKeyword(String keyword) {
        return (keyword != null) ? productRep.findByKeyword(keyword) : null;
    }

    public List<Product> findAllProducts() {
        return productRep.findAll();
    }

    public Page<Product> findProductsByKeywordPaged(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRep.findByKeywordPageable(keyword, pageable);
        totalPages = productPage.getTotalPages();
        return productPage;
    }

    public Page<Product> findAllProductsPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRep.findAll(pageable);
        totalPages = productPage.getTotalPages();
        return productPage;
    }

    public Product saveProduct(Product product) {
        return productRep.save(product);
    }

    public List<Product> findAllProductsByOrderByNameAsc() {
        return productRep.findAllByOrderByNameAsc();
    }

    public Optional<Product> findProductById(Long id) {
        return productRep.findById(id);
    }

    public void deleteProductById(Long id) {
        productRep.deleteById(id);
    }

    ///////////////////////////TRACKS///////////////////////////
    public boolean saveTracks(Product product, List<String> trackNames) {
        List<Track> tracks = new ArrayList<>();
        if (!trackNames.isEmpty()) {
            for (String str : trackNames) {
                tracks.add(new Track(str));
            }
            List<Track> savedTracks = saveAllTracks(tracks);
            product.setTracks(savedTracks);
            if (!product.getTracks().isEmpty()) {
                productRep.save(product);
                return true;
            } else
                return false;
        }
        return false;
    }

    public void deleteAllTracks(Product product) {
        trackRep.deleteAll(product.getTracks());
    }

    public List<Track> saveAllTracks(List<Track> tracks) {
        return trackRep.saveAll(tracks);
    }


    public void removeTracksByIdIn(List<Long> ids) {
        trackRep.removeByIdIn(ids);
    }


    ///////////////////////////IMAGES///////////////////////////
    public List<Images> findImagesByProduct(Product product) {
        return imageRep.findByProduct(product);
    }

    public void deleteAllImages(Product product) {
        imageRep.deleteAll(imageRep.findByProduct(product));
    }

    public void deleteImageById(Long id) {
        imageRep.deleteById(id);
    }

    public void saveImage(Product product, MultipartFile file) throws IOException {
        String uploadDir = imageStorageUrl + product.getId();
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
        String imageURIrel = String.valueOf(filePath).substring(String.valueOf(filePath).indexOf("static") + 6);

        Images image = new Images();
        image.setProduct(product);
        image.setImage(imageURIrel);
        deleteAllImages(product);
        imageRep.save(image);
    }
}
