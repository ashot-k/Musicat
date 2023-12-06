package com.tutorial.ecommercebackend.service;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.repository.TrackRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    ProductRepository productRep;
    TrackRepository trackRep;

    String imageStorageUrl = "./src/main/resources/static/images/album-images/";
    public static int totalPages;

    @Autowired
    public ProductServiceImpl(ProductRepository products, TrackRepository trackRep) {
        this.productRep = products;
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

        for (Track t : product.getTracks()) {
            t.setProduct(product);
        }
        return productRep.save(product);
    }

    public List<Product> findAllProductsByOrderByNameAsc() {
        return productRep.findAllByOrderByNameAsc();
    }

    public List<Product> findAllRelatedProducts(Long id) {

        return null;
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
              /*  for (Track t : product.getTracks()) {
                    if (t.getName().equals(str))
                        trackNames.remove(str);
                }*/
                tracks.add(new Track(str, product));
            }
            for (Track newTrack : tracks) {
                if(!product.getTracks().contains(newTrack))
                    product.getTracks().add(newTrack);
            }
            productRep.save(product);
            System.out.println(product);
          //  saveAllTracks(tracks);

           /*   List<Track> savedTracks = saveAllTracks(tracks);
             product.setTracks(savedTracks);
            if (!product.getTracks().isEmpty()) {
                productRep.save(product);
                return true;
            } else
                return false;
        }
        return false;*/
        }
        return true;
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



}
